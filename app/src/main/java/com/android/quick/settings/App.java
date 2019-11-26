package com.android.quick.settings;

public class App {
    public String name;
    public int drawable;
    public String pkgName;
    public String clsName;
    public String fragment;

    public App(String name, int drawable, String pkgName, String clsName, String fragment) {
        this.name = name;
        this.drawable = drawable;
        this.pkgName = pkgName;
        this.clsName = clsName;
        this.fragment = fragment;
    }
}
