package com.bebound.template.request.getcharacter.bebound.listeners;

import android.util.Log;

import com.bebound.sdk.engine.listener.request.OnSentListener;

public class MySentListener {

    /**
     * OnSentListener implementation
     */
    public static OnSentListener getListener() {
        return (context, request) -> Log.d(MySentListener.class.getSimpleName(), "Request sent");
    }
}
