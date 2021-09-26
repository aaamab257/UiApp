package com.aaamab.uiapp.ui.register;

public interface RegisterLis {

    public void onEmptyFields(String msg);
    public void onPasswordsLength();
    public void onPasswordsNotMatching();
    public void onSuccessfully(String email , String name , String password);

}
