package com.idscodelabs.smallholdingscraper.features.scraping

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.devtools.v128.network.Network.*
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass


interface SeleniumScraper<T: Any, U>: Scraper<T, U>{

    val responseClass: KClass<T>
    val responseFilterUrl: String



    companion object{

        val options = ChromeOptions().also {
            it.addArguments("-headless")
        }

        val driver = ChromeDriver(options)
    }


    override fun getDocument(link: String): T {
        val devTool = driver.devTools
        devTool.createSession()

        devTool.send(enable(Optional.empty(), Optional.empty(), Optional.empty()));
        return runBlocking {
            suspendCoroutine {cont->
                devTool.addListener(responseReceived()){
                    runBlocking {
                        delay(1000)
                    }

                    if(it.response.url == responseFilterUrl){
                        try{
                            val str = devTool.send(getResponseBody(it.requestId)).body
                            val result = jacksonObjectMapper().readValue(str, responseClass.java)
                            cont.resume(result)
                        } catch (e: Throwable){
                            e.printStackTrace()
                        }
                    }
                }
                driver.get(link)
            }
        }
    }





}