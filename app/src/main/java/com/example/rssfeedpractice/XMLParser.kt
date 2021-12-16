package com.example.rssfeedpractice

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XMLParser {
    private var  text:String? =null

    private lateinit var questions:ArrayList<Question>
    private var questionTitle = ""
    private var questionAuthor = ""
    private var questionCategories = ""
    private var questionDetails = ""


    fun pars(): ArrayList<Question> {
        questions = arrayListOf()
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("https://stackoverflow.com/feeds" )
            parser.setInput(url.openStream(), null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType){
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG ->  when {
                        tagName.equals("title", true) -> {
                            questionTitle = text.toString()
                        }
                        tagName.equals("name", true) -> {
                            questionAuthor = text.toString()
                        }
                        tagName.equals("summary", true) -> {
                            questionDetails = text.toString()
                        }
                        tagName.equals("entry", true) -> {
                            questionCategories = questionCategories.removeSuffix(", ")
                            questions.add(Question(questionTitle, questionAuthor, questionCategories, questionDetails))
                            questionCategories = ""
                        }
                        else -> {}
                    }
                    else -> {}
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException){ e.printStackTrace() }
        catch (e: IOException){ e.printStackTrace()}
        return questions
    }


}