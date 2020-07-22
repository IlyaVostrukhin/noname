package dev.noname.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebFilter("/*")
public class TrimResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        TrimResponse trimResponse = new TrimResponse((HttpServletResponse) response);
        chain.doFilter(request, trimResponse);
        trimResponse.complete();
    }

    @Override
    public void destroy() {
    }

    private static class TrimResponse extends HttpServletResponseWrapper {
        private final TrimProxyWriter trimProxyWriter;

        private TrimResponse(HttpServletResponse response) throws IOException {
            super(response);
            trimProxyWriter = new TrimProxyWriter(response.getWriter());
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {
                }

                @Override
                public void write(int b) throws IOException {
                    trimProxyWriter.write(b);
                }
            };
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(trimProxyWriter);
        }

        private void complete() throws IOException {
            setContentLength(trimProxyWriter.getLength());
            trimProxyWriter.complete();
        }
    }

    private static class TrimProxyWriter extends Writer {
        private final Writer writer;
        private int length;

        private TrimProxyWriter(Writer writer) {
            super();
            this.writer = writer;
            this.length = 0;
        }

        @Override
        public void write(int c) throws IOException {
            processChar((char) c);
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (int i = off; i < len; i++) {
                processChar(cbuf[i]);
            }
        }

        @Override
        public void write(String str, int off, int len) throws IOException {
            for (int i = off; i < len; i++) {
                processChar(str.charAt(i));
            }
        }

        private void processChar(char ch) throws IOException {
            if (ch != '\t' && ch != '\r' && ch != '\n') {
                writer.write(ch);
                length++;
            }
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
        }

        private int getLength() {
            return length;
        }

        private void complete() throws IOException {
            writer.flush();
            writer.close();
        }
    }
}
