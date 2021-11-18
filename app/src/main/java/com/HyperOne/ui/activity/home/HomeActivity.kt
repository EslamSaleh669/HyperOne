package com.HyperOne.ui.activity.home

import android.app.AlertDialog
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.HyperOne.R
import com.HyperOne.data.model.DataResponseItem
import com.HyperOne.di.room.entity.ItemModel
import com.HyperOne.ui.adapter.DataAdapter
import com.HyperOne.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_viewshape.*
import org.jetbrains.anko.doAsync
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() , DataAdapter.OnDownloadCLickListener {

    @Inject
    @field:Named("main")
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    lateinit var offlineViewModel: itemsViewModel

    val DBList = ArrayList<Int>()


    private val autoDispose: AutoDispose = AutoDispose()
    var dialog : Dialog? = null

    var progressStatus = 0
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as MyApplication).appComponent?.inject(this)
        autoDispose.bindTo(this.lifecycle)


        offlineViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(itemsViewModel::class.java)

         offlineViewModel.allItems.observe(this, androidx.lifecycle.Observer { items ->
            Log.d("dataishere", items.toString())


            if (items.size > 0){

                for (x in items){
                    DBList.add(x.itemid)
                }

            }
            getData(DBList)


        })


        handler = Handler(Handler.Callback {
            true
        })

        dialog = launchLoadingDialog()

    }

    fun getData( OfflineList : ArrayList<Int>) {

        autoDispose.add(

            viewModel.getData().observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    if (it.size > 0){

                        datarecycler.adapter =
                            DataAdapter(it, this,this,OfflineList)
                        datarecycler.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

                        dialog!!.dismiss()
                        Log.d("errorrespone",it.toString())

                    }



                }, {
                    dialog!!.dismiss()
                    Timber.e(it)

                    makeToast(getString(R.string.networkerror))
                })
        )
    }
    fun downloadService(url: String, title: String, type: String): Long {
        showCustomDialog()

        val downloadReference: Long
        val dm: DownloadManager =
            getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)

        val request = DownloadManager.Request(uri)
        if (type.equals("VIDEO")){
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "mydata.mp4"
            )
        }else{
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "mydata.pdf"
            )
        }

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(title)
         makeToast(getString(R.string.start_down))

        downloadReference = dm.enqueue(request) ?: 0
        return downloadReference
    }

    private fun showCustomDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_download, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        val progressBar = mDialogView.findViewById(R.id.progressBar) as ProgressBar
        val textView = mDialogView.findViewById(R.id.textView) as TextView

        progressBar.progress = 0
        progressStatus = 0


        val filesToDownload = kotlin.random.Random.nextInt(10,100)

         progressBar.max = filesToDownload
        Thread(Runnable {
            while (progressStatus < filesToDownload) {
                progressStatus += 1
                Thread.sleep(50)

                handler!!.post {
                    progressBar.progress = progressStatus

                    val percentage = ((progressStatus.toDouble()
                            / filesToDownload) * 100).toInt()


                    textView.text = "($percentage%) Downloaded "

                    if (progressStatus == filesToDownload) {
                        mAlertDialog.dismiss()

                    }
                }
            }
        }).start()
    }

    override fun onDawnloadClicked(item: DataResponseItem) {

        doAsync{
            offlineViewModel.insert(ItemModel(item.id!!))
        }

        showCustomDialog()
        
       // downloadService(item.url!!, item.name!!,item.type!!)

    }
}