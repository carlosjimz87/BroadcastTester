package com.carlosjimz.broadcasttester.utils

import android.content.Intent
import androidx.test.ext.truth.content.IntentSubject
import com.carlosjimz.broadcasttester.Constants
import com.google.common.truth.Truth


fun extraAssertions(intent: Intent) {
    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraBoolean.first)
    val extraBool = intent.getBooleanExtra(Constants.ExtraBoolean.first, false)
    Truth.assertThat(extraBool).isEqualTo(Constants.ExtraBoolean.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraString.first)
    val extraString = intent.getStringExtra(Constants.ExtraString.first)
    Truth.assertThat(extraString).isEqualTo(Constants.ExtraString.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraInt.first)
    val extraInt = intent.getIntExtra(Constants.ExtraInt.first, 0)
    Truth.assertThat(extraInt).isEqualTo(Constants.ExtraInt.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraFloat.first)
    val extraFloat = intent.getFloatExtra(Constants.ExtraFloat.first, 0f)
    Truth.assertThat(extraFloat).isEqualTo(Constants.ExtraFloat.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraDouble.first)
    val extraDouble = intent.getDoubleExtra(Constants.ExtraDouble.first, 0.0)
    Truth.assertThat(extraDouble).isEqualTo(Constants.ExtraDouble.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraChar.first)
    val extraChar = intent.getCharExtra(Constants.ExtraChar.first, '_')
    Truth.assertThat(extraChar).isEqualTo(Constants.ExtraChar.second)

    IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraLong.first)
    val extraLong = intent.getLongExtra(Constants.ExtraLong.first, 0)
    Truth.assertThat(extraLong).isEqualTo(Constants.ExtraLong.second)
}
