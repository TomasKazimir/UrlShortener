package com.kazous.urlshortener.util;

public class Base62Encoder implements Encoder {
    private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = CHARSET.length();

    /**
     * Encodes a long value into a Base62 string representation.
     *
     * @param number the long value to be encoded
     * @return a Base62 string representation of the encoded long value
     */
    @Override
    public String encode(long number) {
        if (number == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            sb.append(CHARSET.charAt(remainder));
            number /= BASE;
        }
        return sb.reverse().toString();
    }

}
