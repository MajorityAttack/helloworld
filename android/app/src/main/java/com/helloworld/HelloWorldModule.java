package com.helloworld;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.bridge.Promise;

import java.util.Map;
import java.util.HashMap;
 
public class HelloWorldModule extends ReactContextBaseJavaModule {
 
    static {
        System.loadLibrary("native-lib");
    }

    public HelloWorldModule(ReactApplicationContext reactContext) {
        super(reactContext); //required by React Native
    }
 
    @Override
    //getName is required to define the name of the module represented in JavaScript
    public String getName() { 
        return "HelloWorld";
    }

    @Override
    public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("SHORT", Toast.LENGTH_SHORT);
      constants.put("LONG", Toast.LENGTH_LONG);
      return constants;
    }
 
    @ReactMethod
    public void sayHi(Callback errorCallback, Callback successCallback) {
        try {
            System.out.println("Greetings from Java");
            successCallback.invoke("Callback : Greetings from Java");
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void show(String message, int duration) {
      Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void hello(Promise promise) {
      promise.resolve("Hello!");
    }

    @ReactMethod
    public void helloJNI(Promise promise) {
      promise.resolve(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
