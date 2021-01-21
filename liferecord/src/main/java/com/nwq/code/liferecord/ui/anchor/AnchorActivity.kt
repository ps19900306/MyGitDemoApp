package com.nwq.code.liferecord.ui.anchor

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.ListPopupWindow
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ningwenqiang.glory.toollibrary.activity.BasicActivity
import com.ningwenqiang.glory.toollibrary.observer.DataInterface
import com.ningwenqiang.glory.toollibrary.toast.ToastUtil
import com.nwq.code.liferecord.R
import com.nwq.code.liferecord.data_base.GetDaoSession
import kotlinx.android.synthetic.main.activity_anchor.*
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.DialogLayer
import per.goweii.anylayer.SwipeLayout

class AnchorActivity : BasicActivity(), DataInterface<AnchorLiveData>, GetDaoSession {

    private val anchorLiveData by lazy {
        AnchorLiveData()
    }
    private lateinit var listPop: ListPopupWindow
    val list by lazy {
        listOf(
            "UNDEFINED",
            "TIME",
            "TARGET",
            "SOCIAL_CONTACT"
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anchor)
        fab.setOnClickListener {
            //   val daoSession: DaoSession = (application as NwqApp).daoSession
            if (anchorLiveData.hasInputContent()) {
                showSelect()
            }else{
                ToastUtil.showToastLong("请先输入内容")
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

    fun showSelect() {
        val dialog = AnyLayer.dialog(this)
            .contentView(R.layout.dilog_anchor_step)
            .avoidStatusBar(true)
            .gravity(Gravity.TOP)
            .outsideInterceptTouchEvent(false)
            .swipeDismiss(SwipeLayout.Direction.RIGHT or SwipeLayout.Direction.LEFT or SwipeLayout.Direction.TOP)
            .animStyle(DialogLayer.AnimStyle.TOP)
        dialog.bindData {
            val choiceView = it.getView<TextView>(R.id.choice)
             listPop = ListPopupWindow(this)

             listPop.setAdapter(
                ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    list
                )
            )

            listPop.width = ViewGroup.LayoutParams.WRAP_CONTENT
            listPop.height = ViewGroup.LayoutParams.WRAP_CONTENT
            listPop.anchorView = choiceView //设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
            listPop.isModal = true //设置是否是模式
            listPop.setOnItemClickListener { parent, view, position, id ->
                choiceView?.text = list[position]
                listPop.dismiss()
            }
            choiceView?.setOnClickListener {
                if (listPop.isShowing) {
                    listPop.dismiss()
                } else {
                    listPop.show()
                }
            }
        }
        dialog.show()
    }


}