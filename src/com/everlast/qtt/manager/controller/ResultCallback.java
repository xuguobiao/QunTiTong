package com.everlast.qtt.manager.controller;

/**
 * @author: XuGuobiao
 * @email: everlastxgb@gmail.com
 *
 * @create_time: 2015-9-1 ����6:00:21
 *
 */
public abstract class ResultCallback<M extends Object> {

    public ResultCallback() {
    }

    public void callbackInUI(final boolean success, final Object resultObject, final String error) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                onResult(success, (M)resultObject, error);
            }
        });
    }

    public abstract void onResult( boolean success, M result, String error);

    public void onRestoreData(Object data) {

    }
}
