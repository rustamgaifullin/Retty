package com.rm.retty.server;

public class Request {
    private final String body;

    public Request(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return body != null ? body.equals(request.body) : request.body == null;
    }

    @Override
    public int hashCode() {
        return body != null ? body.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Request{" +
                "body='" + body + '\'' +
                '}';
    }
}