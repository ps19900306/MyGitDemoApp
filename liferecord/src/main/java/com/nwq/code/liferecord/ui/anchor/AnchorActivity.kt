package com.nwq.code.liferecord.ui.anchor

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ningwenqiang.glory.toollibrary.activity.BasicActivity
import com.ningwenqiang.glory.toollibrary.observer.DataInterface
import com.nwq.code.liferecord.R
import com.nwq.code.liferecord.data_base.GetDaoSession
import com.nwq.code.liferecord.ui.anchor.AnchorLiveData
import kotlinx.android.synthetic.main.activity_anchor.*
import per.goweii.anylayer.AnyLayer

class AnchorActivity : BasicActivity(), DataInterface<AnchorLiveData>, GetDaoSession {

    private val anchorLiveData by lazy {
        AnchorLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anchor)
        fab.setOnClickListener {
            //   val daoSession: DaoSession = (application as NwqApp).daoSession
            if (anchorLiveData.hasInputContent()) {

            }
        }
        supportActionBar?.hide()
        //底部
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_text, R.id.navigation_audio, R.id.navigation_photograph
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun getData(): AnchorLiveData {
        return anchorLiveData
    }

    fun saveData() {
        getDaoSession().anchorPointDao.save(anchorLiveData.value)
    }

    fun showSelect(){

    }
}