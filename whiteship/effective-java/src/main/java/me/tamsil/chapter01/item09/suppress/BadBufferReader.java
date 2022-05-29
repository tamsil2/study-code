package me.tamsil.chapter01.item09.suppress;

import java.io.*;

public class BadBufferReader extends BufferedReader {
    public BadBufferReader(Reader in, int sz) {
        super(in, sz);
    }

    public BadBufferReader(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        throw new CharConversionException();
    }

    @Override
    public void close() throws IOException {
        throw new StreamCorruptedException();
    }
}
