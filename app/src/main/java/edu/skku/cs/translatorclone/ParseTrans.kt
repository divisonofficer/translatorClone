package edu.skku.cs.translatorclone

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception

class ParseTrans {



    companion object{




        fun getTranslate(text : String) : String
        {
            return try {
                val doc = prepareDoc(text)

                doc.select("span[class=\"Q4iAWc\"]").text()
            } catch(e:Exception) {
                e.printStackTrace()

                ""
            }
        }

        fun prepareDoc(text : String) : Document{

            return Jsoup.connect(String.format(request,text)).get()
        }


        const val request = "https://translate.google.com/?sl=en&tl=ko&text=%s&op=translate"
    }
}