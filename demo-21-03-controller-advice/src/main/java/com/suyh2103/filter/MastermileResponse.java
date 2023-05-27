package com.suyh2103.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 参考博客：https://www.codenong.com/21882060/
 * 重写response
 */
public class MastermileResponse extends HttpServletResponseWrapper {

    private final CharArrayWriter bufferWriter;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final HttpServletResponse httpServletResponse;

    public MastermileResponse(HttpServletResponse response) {
        super(response);
        bufferWriter = new CharArrayWriter();
        httpServletResponse = response;
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStreamWrapper(this.byteArrayOutputStream, this.httpServletResponse);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(bufferWriter);
    }

    public String getResult() {
        return bufferWriter.toString();
    }

    public String getContent() {
        return new String(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void setContentType(String type) {
        super.setContentType(type);
    }

    private static class ServletOutputStreamWrapper extends ServletOutputStream {

        private final ByteArrayOutputStream outputStream;
        private final HttpServletResponse response;

        public ServletOutputStreamWrapper(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response) {
            this.outputStream = byteArrayOutputStream;
            this.response = response;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }

        @Override
        public void write(int b) throws IOException {
            this.outputStream.write(b);
        }

        @Override
        public void flush() throws IOException {
            if (!this.response.isCommitted()) {
                byte[] body = this.outputStream.toByteArray();
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(body);
                outputStream.flush();
            }
        }
    }
}