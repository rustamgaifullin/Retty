package com.rm.retty.container;

public class Response {
    private final String body;
    private final Integer code;


    public Response(String body, Integer code) {
        this.body = body;
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (body != null ? !body.equals(response.body) : response.body != null) return false;
        return code != null ? code.equals(response.code) : response.code == null;
    }

    @Override
    public int hashCode() {
        int result = body != null ? body.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                ", code=" + code +
                '}';
    }
}
