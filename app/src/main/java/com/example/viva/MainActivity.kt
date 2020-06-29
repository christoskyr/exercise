package com.example.viva

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viva.event.ProductClickedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


const val TAG__FRAGMENT = "Fragment"

class MainActivity : AppCompatActivity() {

    private var mFragment: MainActivityFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            mFragment = MainActivityFragment()
            fragmentTransaction.replace(R.id.fragment_container,
                mFragment!!, TAG__FRAGMENT)
            fragmentTransaction.commit()
        } else {
            mFragment = supportFragmentManager.findFragmentByTag(TAG__FRAGMENT) as MainActivityFragment
        }
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductClickedEventReceived(event: ProductClickedEvent) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.MODEL, event.product)
        startActivity(intent)
    }
}
