package com.mahmoud.decadeofmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_toolbar.*

abstract class ToolbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        setSupportActionBar(toolbar)

        // Setup the toolbar navigation icon
        if (shouldShowUpButton()) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        } else {
            toolbar.navigationIcon = null
            toolbar.setNavigationOnClickListener(null)
        }

        // Setup the toolbar title
        toolbar.title = getToolbarTitle()
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))

        // Add the Activity main view to the hierarchy
        val activityViewRes = getViewResource()
        layoutInflater.inflate(activityViewRes, activityViewContainer, true)

        // Do whatever
        afterViewInflation()
    }

    // If it was not overwrote, it will show the app name by default
    open fun getToolbarTitle(): String? = getString(R.string.app_name)

    open fun shouldShowUpButton(): Boolean = false

    abstract fun getViewResource(): Int

    abstract fun afterViewInflation()
}