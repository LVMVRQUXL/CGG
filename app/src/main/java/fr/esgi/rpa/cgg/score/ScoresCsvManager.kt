package fr.esgi.rpa.cgg.score

import android.content.Context
import android.util.Log
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVRecord
import java.io.*

class ScoresCsvManager(private val context: Context) {
    companion object {
        private const val FILE_NAME: String = "data.csv"
        private const val TAG: String = "ScoresCsvManager"
    }

    private val filePath = "${this.context.filesDir.path}/$FILE_NAME"
    private var writer: BufferedWriter? = null
    private var reader: BufferedReader? = null

    fun lastScore(): Score? = try {
        this.read().last()
    } catch (exception: NoSuchElementException) {
        Log.i(TAG, "No score is available!", exception)
        null
    }

    fun read(): List<Score> {
        val scores: MutableList<Score> = mutableListOf()
        if (this.exist()) {
            val fileReader = FileReader(this.filePath)
            this.reader = BufferedReader(fileReader)
            val parser = CSVParser(this.reader, CSVFormat.DEFAULT)
            parser.forEach { csvRecord: CSVRecord? ->
                csvRecord?.let { record: CSVRecord ->
                    val id = record.get(0).trim().toInt()
                    val fullScore = record.get(2).trim().split("/")
                    val score = fullScore[0].toInt()
                    val roundsNumber = fullScore[1].toInt()
                    scores.add(Score(id, score, roundsNumber))
                }
            }
        }
        return scores
    }

    fun write(scoreValue: Int, roundsNumber: Int) {
        val scores: MutableList<Score> = this.read() as MutableList<Score>
        val id: Int = scores.size + 1
        val newScore = Score(id, scoreValue, roundsNumber)
        scores.add(newScore)
        val fileWriter = FileWriter(this.filePath)
        this.writer = BufferedWriter(fileWriter)
        val writer = CSVPrinter(this.writer, CSVFormat.DEFAULT)
        scores.forEach { score: Score ->
            writer.printRecord(score.id, score.difficulty(), score.value())
        }
        writer.flush()
        writer.close()
    }

    private fun exist(): Boolean = File(this.filePath).exists()
}