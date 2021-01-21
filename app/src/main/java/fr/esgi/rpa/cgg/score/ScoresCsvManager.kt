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
        if (this.exist()) {
            fileReader = BufferedReader(FileReader(filePath))
            val parser = CSVParser(fileReader, CSVFormat.DEFAULT)
            for (csvRecord in parser) {
                val id = csvRecord.get(0).trim()
                val fullScore = csvRecord.get(2).trim().split("/")
                val score = fullScore[0].toInt()
                val roundsNumber = fullScore[1].toInt()
                this.scores.add(Score(id, score, roundsNumber))
            }
        }
        return this.scores
    }

    fun write(scoreValue: Int, roundsNumber: Int) {
        val id = this.read().size + 1
        Log.v("Score", "id : $id")
        fileWriter = BufferedWriter(FileWriter(filePath))
        val writer = CSVPrinter(fileWriter, CSVFormat.DEFAULT)
        for (score in this.read()) {
            writer.printRecord(score.id(), score.difficulty(), score.value())
        }
        val newScore = Score(id.toString(), scoreValue, roundsNumber)
        writer.printRecord(newScore.id(), newScore.difficulty(), newScore.value())
        writer.flush()
        writer.close()
    }

    private fun exist(): Boolean = File(filePath).exists()


}