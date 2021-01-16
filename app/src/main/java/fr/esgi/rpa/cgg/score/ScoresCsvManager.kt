package fr.esgi.rpa.cgg.score

import android.content.Context
import android.util.Log
import org.apache.commons.csv.*
import java.io.*

class ScoresCsvManager(private val context: Context) {

    private val fileName = "data.csv"
    private val filePath = context.filesDir.path + "/" + fileName
    private var fileReader: BufferedReader? = null
    private var fileWriter: BufferedWriter? = null
    private val scores: MutableList<Score> = mutableListOf()


    fun read(): MutableList<Score> {
        this.exist()
        fileReader = BufferedReader(FileReader(filePath))
        val parser = CSVParser(fileReader, CSVFormat.DEFAULT)
        for (csvRecord in parser) {
            val id = csvRecord.get(0).trim()
            val difficulty = csvRecord.get(1).trim()
            val score = csvRecord.get(2).trim().toInt()
            this.scores.add(Score(id, difficulty, score))
        }
        return this.scores
    }

    fun write(difficulty: String, score: Int) {
        val id = this.read().size + 1
        Log.v("Score", "id : $id")
        fileWriter = BufferedWriter(FileWriter(filePath))
        val writer = CSVPrinter(fileWriter, CSVFormat.DEFAULT)
        for (score in this.read()) {
            writer.printRecord(score.id, score.difficulty, score.value.toString())
        }
        writer.printRecord(id.toString(), difficulty, score.toString())
        writer.flush()
        writer.close()
    }

    private fun exist() {
        try {
            val file = File(filePath)
            val f = file.exists()
            if (f) {
                Log.v("Score", "File exists : $f")
            }
        }
        catch (e: FileNotFoundException) {
            Log.v("Score", "File exists : ${e.message}")
        }
    }


}