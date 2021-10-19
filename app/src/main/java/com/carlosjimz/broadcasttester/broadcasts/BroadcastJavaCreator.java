package com.carlosjimz.broadcasttester.broadcasts;

import android.content.Context;
import android.content.Intent;

import com.carlosjimz.broadcasttester.Constants;

public class BroadcastJavaCreator {
    public Intent sendIntent(Context context) {

        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.setFlags(Constants.FLAG);
        intent.putExtra(Constants.INSTANCE.getExtraString().getFirst(), Constants.INSTANCE.getExtraString().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraBoolean().getFirst(), Constants.INSTANCE.getExtraBoolean().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraInt().getFirst(), Constants.INSTANCE.getExtraInt().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraFloat().getFirst(), Constants.INSTANCE.getExtraFloat().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraDouble().getFirst(), Constants.INSTANCE.getExtraDouble().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraLong().getFirst(), Constants.INSTANCE.getExtraLong().getSecond());
        intent.putExtra(Constants.INSTANCE.getExtraChar().getFirst(), Constants.INSTANCE.getExtraChar().getSecond());
        context.sendBroadcast(intent);
        return intent;
    }
}
