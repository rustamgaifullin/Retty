package com.rm.retty.server;

import java.util.StringTokenizer;

public class MethodPath {
    private final MethodType type;
    private final String path;
    private final MethodParameters parameters;

    public static MethodPath methodPathFromStringRequest(String stringRequest) {
        StringTokenizer firstLineTokenizer = new StringTokenizer(stringRequest, " ");

        MethodType type = MethodType.valueOf(firstLineTokenizer.nextToken());
        String fullMethod = firstLineTokenizer.nextToken();

        StringTokenizer methodTokenizer = new StringTokenizer(fullMethod, "?");
        String method = methodTokenizer.nextToken();

        MethodParameters parameters = methodTokenizer.hasMoreTokens()
                ? MethodParameters.parseFromString(methodTokenizer.nextToken())
                : MethodParameters.noParameters();

        return new MethodPath(type, method, parameters);
    }

    private MethodPath(MethodType type, String path, MethodParameters parameters) {
        this.type = type;
        this.path = path;
        this.parameters = parameters;
    }

    public String getTypedPath() {
        return String.format("%s %s", type.name(), path);
    }

    public MethodType getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public MethodParameters getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodPath that = (MethodPath) o;

        if (type != that.type) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        return parameters != null ? parameters.equals(that.parameters) : that.parameters == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MethodPath{" +
                "type=" + type +
                ", path='" + path + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}