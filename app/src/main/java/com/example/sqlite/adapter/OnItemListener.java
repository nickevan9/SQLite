package com.example.sqlite.adapter;

public interface OnItemListener<T> {
    public void deleteItem(T data);
    public void editItem(T data);
    public void onClickItem(T data);

}
