package com.carlosjimz.broadcasttester

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.carlosjimz.broadcasttester.broadcasts.BroadcastFactory
import com.carlosjimz.broadcasttester.broadcasts.BroadcastJavaCreator
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@LargeTest
class BroadcastTest {

    lateinit var intents: MutableList<Intent>

    lateinit var latch: CountDownLatch

    private lateinit var receiver: BroadcastReceiverTester

    inner class BroadcastReceiverTester : BroadcastReceiver() {

        override fun onReceive(p0: Context?, intent: Intent?) {
            intent?.let {
                intents.add(it)
                latch.countDown()
            }
        }
    }

    private val context: Context = getInstrumentation().targetContext

    @Before
    fun setUp() {
        intents = mutableListOf()
        latch = CountDownLatch(1)
        receiver = BroadcastReceiverTester()

        LocalBroadcastManager.getInstance(context).registerReceiver(
            receiver,
            IntentFilter.create(
                Constants.ACTION,"text/plain"
            )
        )
    }

    @After
    fun tearDown(){
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
    }

    @Test
    fun testBroadcastJavaCreator() {

        val intent = BroadcastJavaCreator()
            .sendIntent(context)

        // assert intent creation
        IntentSubject.assertThat(intent).hasAction(Constants.ACTION)
        IntentSubject.assertThat(intent).hasFlags(Constants.FLAG)

        // assert extras
        extraAssertions(intent)

        // assert broadcast reception (NOT WORKING)
//        latch.await(10,TimeUnit.SECONDS)
//        assertThat(intents.size).isEqualTo(1)
    }

    @Test
    fun testBroadcastKtCreator() {
        val intent = BroadcastFactory.build(
            Constants.ACTION, Constants.FLAG,
            mapOf(
                Constants.ExtraBoolean,
                Constants.ExtraString,
                Constants.ExtraInt,
                Constants.ExtraDouble,
                Constants.ExtraFloat,
                Constants.ExtraChar,
                Constants.ExtraLong,
            )
        ).send(context)

        // assert intent creation
        IntentSubject.assertThat(intent).hasAction(Constants.ACTION)
        IntentSubject.assertThat(intent).hasFlags(Constants.FLAG)

        // assert extras
        extraAssertions(intent)

        // assert broadcast reception (NOT WORKING)
//        latch.await(10,TimeUnit.SECONDS)
//        assertThat(intents.size).isEqualTo(1)
    }


    private fun extraAssertions(intent: Intent) {
        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraBoolean.first)
        val extraBool = intent.getBooleanExtra(Constants.ExtraBoolean.first, false)
        assertThat(extraBool).isEqualTo(Constants.ExtraBoolean.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraString.first)
        val extraString = intent.getStringExtra(Constants.ExtraString.first)
        assertThat(extraString).isEqualTo(Constants.ExtraString.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraInt.first)
        val extraInt = intent.getIntExtra(Constants.ExtraInt.first, 0)
        assertThat(extraInt).isEqualTo(Constants.ExtraInt.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraFloat.first)
        val extraFloat = intent.getFloatExtra(Constants.ExtraFloat.first, 0f)
        assertThat(extraFloat).isEqualTo(Constants.ExtraFloat.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraDouble.first)
        val extraDouble = intent.getDoubleExtra(Constants.ExtraDouble.first, 0.0)
        assertThat(extraDouble).isEqualTo(Constants.ExtraDouble.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraChar.first)
        val extraChar = intent.getCharExtra(Constants.ExtraChar.first, '_')
        assertThat(extraChar).isEqualTo(Constants.ExtraChar.second)

        IntentSubject.assertThat(intent).extras().containsKey(Constants.ExtraLong.first)
        val extraLong = intent.getLongExtra(Constants.ExtraLong.first, 0)
        assertThat(extraLong).isEqualTo(Constants.ExtraLong.second)
    }

}