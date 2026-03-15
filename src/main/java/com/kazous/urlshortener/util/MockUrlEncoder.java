package com.kazous.urlshortener.util;

public class MockUrlEncoder implements Encoder {
    public String encode(long input) {
        return "encoded_" + input;
    }
}
