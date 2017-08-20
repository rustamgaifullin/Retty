package com.rm.retty.server;

public class Response {
    private final String body;

    public Response(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        return body != null ? body.equals(response.body) : response.body == null;
    }

    @Override
    public int hashCode() {
        return body != null ? body.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                '}';
    }
}