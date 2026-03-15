package com.kazous.urlshortener.util;

public interface Encoder {
    /**
     * Encodes a long value into a string representation.
     *
     * @param input the long value to be encoded
     * @return a string representation of the encoded long value
     */
    String encode(long input);
}
