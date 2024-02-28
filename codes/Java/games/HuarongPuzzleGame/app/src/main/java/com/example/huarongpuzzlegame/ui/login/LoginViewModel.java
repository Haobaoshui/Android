package com.example.huarongpuzzlegame.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.huarongpuzzlegame.R;
import com.example.huarongpuzzlegame.data.LoginRepository;
import com.example.huarongpuzzlegame.data.Result;
import com.example.huarongpuzzlegame.data.model.LoggedInUser;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // A placeholder username validation check
    private static boolean isUserNameValid(String username) {
        if (username == null) return false;
        if (username.contains("@")) return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        else
            return !username.trim().isEmpty();
    }

    // A placeholder password validation check
    private static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else loginResult.setValue(new LoginResult(R.string.login_failed));
    }

    void loginDataChanged(String username, String password) {
        if (!LoginViewModel.isUserNameValid(username))
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        else if (!LoginViewModel.isPasswordValid(password))
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        else
            loginFormState.setValue(new LoginFormState(true));
    }
}
