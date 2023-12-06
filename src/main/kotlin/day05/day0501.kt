package day05

fun main(args: Array<String>) {
    val translationMap = arrayListOf<ArrayList<Int>>()
    for (_seed in sampleSeeds) {
        val seedTranslation = arrayListOf<Int>()
        for (map in sampleMaps) {
            val seed =  if (seedTranslation.size == 0) _seed else seedTranslation.last
            var translated = 0
            for (submap in map){
//              println("$seed [$submap]")
                val source = submap[1]
                val destination = submap[0]
                val range = submap[2]
//              println("$seed $source $destination $range")
                if (seed >= source && seed <= source+range){
                    translated = destination + (seed - source)
                }
//
            }
            if (translated == 0) { translated = seed }
                seedTranslation.add(translated)
                println("$seed - $translated")
        }
        translationMap.add(seedTranslation)
    }

    println("---------------")
    println("$sampleSeeds")
    println("$translationMap")

    println("result - " + translationMap.map { it.last }.min())
}

val sampleSeeds = arrayListOf(
    79, 14, 55, 13
)

val seedToSoilMap = arrayListOf(
arrayListOf(50, 98, 2),
arrayListOf(52, 50, 48))

val soilToFFertilizerMap = arrayListOf(
arrayListOf(0, 15, 37),
arrayListOf(37, 52, 2),
arrayListOf(39, 0, 15)
)

val fertilizerToWaterMap = arrayListOf(
arrayListOf(49, 53, 8),
arrayListOf(0, 11, 42),
arrayListOf(42, 0, 7),
arrayListOf(57, 7, 4))

val waterToLiightMap = arrayListOf(
arrayListOf(88, 18, 7),
arrayListOf(18, 25, 70))

val lightToTemperatureMap = arrayListOf(
arrayListOf(45,77, 23),
arrayListOf(81, 45, 19),
arrayListOf(68, 64, 13))

val temperatureToHumidityMap = arrayListOf(
arrayListOf(0, 69, 1),
arrayListOf(1, 0, 69))

val humidityToLocationMap = arrayListOf(
arrayListOf(60, 56, 37),
arrayListOf(56, 93, 4))

val sampleMaps = arrayListOf(
    seedToSoilMap,
    soilToFFertilizerMap,
    fertilizerToWaterMap,
    waterToLiightMap,
    lightToTemperatureMap,
    temperatureToHumidityMap,
    humidityToLocationMap,
)

