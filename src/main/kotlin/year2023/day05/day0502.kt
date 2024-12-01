package year2023.day05

fun main(args: Array<String>) {
    val translationMap = arrayListOf<ArrayList<Long>>()
    val seedsList = realSeeds
    val updatedSeedList = arrayListOf<Long>()
    for (i in 0..((seedsList.size-1) /2) ) {
        val _seed = seedsList[i*2].toLong()
        val seedRange = seedsList[i*2+1]
        for (i2 in 0..(seedRange-1)) {
            updatedSeedList.add(_seed + i2)
        }
    }
    println(updatedSeedList)
    for (_seed in updatedSeedList) {
//        println("$i - $_seed - $seedRange ")
        val seedTranslation = arrayListOf<Long>()
        for (map in sampleMaps) {
            val seed : Long =  if (seedTranslation.size == 0) _seed else seedTranslation.last()
            var translated = -1L
            for (submap in map){
//              println("$seed [$submap]")
                val source = submap[1]
                val destination = submap[0]
                val range = submap[2]
//              println("$seed $source $destination $range")
                if (seed >= source && seed <= source+range){
                    if (translated == -1L || translated > (destination + (seed - source))) {
                        translated = destination + (seed - source)
                    }
                }
//
            }
            if (translated == -1L
                ) { translated = seed }
                seedTranslation.add(translated)
                println("$seed - $translated")
        }
        translationMap.add(seedTranslation)
        println("seed = $_seed - Translation = $seedTranslation")
    }

    println("---------------")
    println("$seedsList")
    println("${seedsList.size}")
    println("${updatedSeedList}")
    println("${updatedSeedList.size}")

    println("$translationMap")
    println("${translationMap.size}")

    println("result - " + translationMap.map { it.last() }.min())
}

//val sampleSeeds = arrayListOf(
//    79, 14, 55, 13
//)
//
//val realSeeds = arrayListOf<Long>(
//    3139431799, 50198205, 3647185634, 110151761, 2478641666, 139825503, 498892555, 8913570, 961540761, 489996751, 568452082, 100080382, 907727477, 42158689, 1617552130, 312026427, 342640189, 97088268, 2049289560, 336766062,
//
//)

//val seedToSoilMap = arrayListOf(
//arrayListOf(50, 98, 2),
//arrayListOf(52, 50, 48))
//
//val soilToFFertilizerMap = arrayListOf(
//arrayListOf(0, 15, 37),
//arrayListOf(37, 52, 2),
//arrayListOf(39, 0, 15)
//)
//
//val fertilizerToWaterMap = arrayListOf(
//arrayListOf(49, 53, 8),
//arrayListOf(0, 11, 42),
//arrayListOf(42, 0, 7),
//arrayListOf(57, 7, 4))
//
//val waterToLiightMap = arrayListOf(
//arrayListOf(88, 18, 7),
//arrayListOf(18, 25, 70))
//
//val lightToTemperatureMap = arrayListOf(
//arrayListOf(45,77, 23),
//arrayListOf(81, 45, 19),
//arrayListOf(68, 64, 13))
//
//val temperatureToHumidityMap = arrayListOf(
//arrayListOf(0, 69, 1),
//arrayListOf(1, 0, 69))
//
//val humidityToLocationMap = arrayListOf(
//arrayListOf(60, 56, 37),
//arrayListOf(56, 93, 4))
//val seedToSoilMap = arrayListOf(
//arrayListOf<Long>(1615836342, 1401909974, 23067952),
//arrayListOf<Long>(785532007, 269485885, 88937774),
//arrayListOf<Long>(3019002892, 2773729385, 10470414),
//arrayListOf<Long>(4202163101, 2747292152, 26437233),
//arrayListOf<Long>(3183210415, 4217634159, 77333137),
//arrayListOf<Long>(2847460091, 3211730218, 136699600),
//arrayListOf<Long>(2455891790, 3791729773, 70553041),
//arrayListOf<Long>(3260543552, 2581343101, 165949051),
//arrayListOf<Long>(3840286095, 2849853212, 361877006),
//arrayListOf<Long>(4228600334, 2361239030, 66366962),
//arrayListOf<Long>(1594559581, 1077839137, 21276761),
//arrayListOf<Long>(380069408, 165017790, 44262617),
//arrayListOf<Long>(3598718222, 1894384162, 241567873),
//arrayListOf<Long>(0, 1424977926, 190757551),
//arrayListOf<Long>(1894384162, 2810496375, 39356837),
//arrayListOf<Long>(424332025, 606264721, 196539291),
//arrayListOf<Long>(3521487829, 2221977524, 77230393),
//arrayListOf<Long>(742681934, 69797365, 36566707),
//arrayListOf<Long>(1638904294, 1615735477, 139190145),
//arrayListOf<Long>(1335949488, 0, 69797365),
//arrayListOf<Long>(779248641, 802804012, 6283366),
//arrayListOf<Long>(2638766896, 4008940964, 208693195),
//arrayListOf<Long>(250963029, 1142644585, 70452661),
//arrayListOf<Long>(1933740999, 3470280789, 321448984),
//arrayListOf<Long>(190757551, 209280407, 60205478),
//arrayListOf<Long>(1778094439, 1099115898, 43528687),
//arrayListOf<Long>(2255189983, 3348429818, 121850971),
//arrayListOf<Long>(1000500225, 809087378, 268751759),
//arrayListOf<Long>(1269251984, 1754925622, 66697504),
//arrayListOf<Long>(874469781, 358423659, 126030444),
//arrayListOf<Long>(2526444831, 2135952035, 86025489),
//arrayListOf<Long>(2439072067, 3992121241, 16819723),
//arrayListOf<Long>(3426492603, 3897126015, 94995226),
//arrayListOf<Long>(1405746853, 1213097246, 188812728),
//arrayListOf<Long>(321415690, 106364072, 58653718),
//arrayListOf<Long>(2984159691, 3862282814, 34843201),
//arrayListOf<Long>(2377040954, 2299207917, 62031113),
//arrayListOf<Long>(3029473306, 2427605992, 153737109),
//arrayListOf<Long>(2612470320, 2784199799, 26296576),
//arrayListOf<Long>(620871316, 484454103, 121810618),)
//
//val soilToFFertilizerMap = arrayListOf(
//arrayListOf<Long>(4245401761, 2352458099, 28057201),
//arrayListOf<Long>(2099789767, 3998256334, 14950546),
//arrayListOf<Long>(3446056574, 2749719529, 135349925),
//arrayListOf<Long>(890092371, 1379309857, 42097049),
//arrayListOf<Long>(953714890, 896502554, 10335567),
//arrayListOf<Long>(3115342240, 2380515300, 218129381),
//arrayListOf<Long>(3333471621, 3885671381, 112584953),
//arrayListOf<Long>(663999152, 0, 226093219),
//arrayListOf<Long>(1873325002, 727305635, 169196919),
//arrayListOf<Long>(2042521921, 1328150912, 51158945),
//arrayListOf<Long>(3581406499, 4034715214, 260252082),
//arrayListOf<Long>(500989478, 564295961, 163009674),
//arrayListOf<Long>(4273458962, 4013206880, 21508334),
//arrayListOf<Long>(3992733429, 2099789767, 164092891),
//arrayListOf<Long>(4156826320, 2263882658, 88575441),
//arrayListOf<Long>(3841658581, 2598644681, 151074848),
//arrayListOf<Long>(1094703999, 226093219, 21270249),
//arrayListOf<Long>(1708571521, 399542480, 164753481),
//arrayListOf<Long>(964050457, 247363468, 130653542),
//arrayListOf<Long>(1236290130, 1421406906, 171284482),
//arrayListOf<Long>(0, 1592691388, 500989478),
//arrayListOf<Long>(932189420, 378017010, 21525470),
//arrayListOf<Long>(2114740313, 2885069454, 1000601927),
//arrayListOf<Long>(1115974248, 1207835030, 120315882),
//arrayListOf<Long>(1407574612, 906838121, 300996909),)
//
//
//val fertilizerToWaterMap = arrayListOf(
//arrayListOf<Long>(3217858280, 3761663130, 355893932),
//arrayListOf<Long>(2319366035, 2401839275, 72374872),
//arrayListOf<Long>(1962726423, 909927230, 105011330),
//arrayListOf<Long>(2115307878, 441322644, 204058157),
//arrayListOf<Long>(2095064202, 1824085445, 20243676),
//arrayListOf<Long>(110580631, 329763915, 34129824),
//arrayListOf<Long>(2573484127, 2701101998, 225220022),
//arrayListOf<Long>(1780224111, 2342656863, 59182412),
//arrayListOf<Long>(1717605398, 1571533532, 62618713),
//arrayListOf<Long>(3589165621, 3062909078, 75538793),
//arrayListOf<Long>(842280446, 1871096488, 471560375),
//arrayListOf<Long>(409726333, 0, 243114563),
//arrayListOf<Long>(397401582, 760576019, 12324751),
//arrayListOf<Long>(0, 667815878, 92760141),
//arrayListOf<Long>(3990305711, 2926322020, 5981819),
//arrayListOf<Long>(251664023, 1741105813, 32320840),
//arrayListOf<Long>(2072629125, 645380801, 22435077),
//arrayListOf<Long>(92760141, 363893739, 17820490),
//arrayListOf<Long>(2798704149, 3138447871, 156185660),
//arrayListOf<Long>(3664704414, 2573484127, 127617871),
//arrayListOf<Long>(1839406523, 381714229, 59608415),
//arrayListOf<Long>(283984863, 1844329121, 26767367),
//arrayListOf<Long>(1313840821, 1176023584, 27579684),
//arrayListOf<Long>(2391740907, 1790738543, 33346902),
//arrayListOf<Long>(3929650615, 4234312200, 60655096),
//arrayListOf<Long>(3996287530, 3557602002, 204061128),
//arrayListOf<Long>(1402088224, 772900770, 137026460),
//arrayListOf<Long>(1911435456, 2474214147, 51290967),
//arrayListOf<Long>(1539114684, 1393042818, 178490714),
//arrayListOf<Long>(3573752212, 4117557062, 15413409),
//arrayListOf<Long>(1341420505, 1014938560, 60667719),
//arrayListOf<Long>(3792322285, 4132970471, 6723091),
//arrayListOf<Long>(4200348658, 4139693562, 80082634),
//arrayListOf<Long>(652840896, 1203603268, 189439550),
//arrayListOf<Long>(310752230, 243114563, 86649352),
//arrayListOf<Long>(2425087809, 1075606279, 100417305),
//arrayListOf<Long>(1899014938, 1773426653, 12420518),
//arrayListOf<Long>(2067737753, 1785847171, 4891372),
//arrayListOf<Long>(3799045376, 2932303839, 130605239),
//arrayListOf<Long>(2954889809, 3294633531, 262968471),
//arrayListOf<Long>(4280431292, 4219776196, 14536004),
//arrayListOf<Long>(144710455, 1634152245, 106953568),)
//
//val waterToLiightMap = arrayListOf(
//arrayListOf<Long>(1071107509, 759231097, 26724064),
//arrayListOf<Long>(1293599454, 642189614, 64567949),
//arrayListOf<Long>(3147690498, 1633749175, 71376364),
//arrayListOf<Long>(3487999223, 4080968704, 155844998),
//arrayListOf<Long>(1700873635, 2097781236, 292450760),
//arrayListOf<Long>(1146121952, 1950303734, 147477502),
//arrayListOf<Long>(2864027062, 1470573702, 75076585),
//arrayListOf<Long>(2507471274, 3021419800, 175807670),
//arrayListOf<Long>(2939103647, 922829268, 123372939),
//arrayListOf<Long>(2826742681, 2551285626, 37284381),
//arrayListOf<Long>(719212681, 1821763210, 100196810),
//arrayListOf<Long>(4236813702, 4292227071, 2740225),
//arrayListOf<Long>(3643844221, 3837685117, 243283587),
//arrayListOf<Long>(209103905, 785955161, 52302440),
//arrayListOf<Long>(100332146, 752700417, 6530680),
//arrayListOf<Long>(1478192138, 3229160565, 10342236),
//arrayListOf<Long>(1358167403, 3239502801, 94242072),
//arrayListOf<Long>(3219066862, 706757563, 45942854),
//arrayListOf<Long>(3062476586, 1358475140, 29661777),
//arrayListOf<Long>(819409491, 2390231996, 26561329),
//arrayListOf<Long>(4195136152, 3511184798, 41677550),
//arrayListOf<Long>(1488534374, 1046202207, 35444248),
//arrayListOf<Long>(3887127808, 3552862348, 284822769),
//arrayListOf<Long>(2188804057, 1705125539, 116637671),
//arrayListOf<Long>(0, 309410320, 100332146),
//arrayListOf<Long>(1117778238, 1921960020, 28343714),
//arrayListOf<Long>(261406345, 3333744873, 74978759),
//arrayListOf<Long>(2100705169, 1545650287, 88098888),
//arrayListOf<Long>(3450098653, 3473284228, 37900570),
//arrayListOf<Long>(2742067948, 1273800407, 84674733),
//arrayListOf<Long>(2305441728, 107380774, 202029546),
//arrayListOf<Long>(3265009716, 1130086491, 143713916),
//arrayListOf<Long>(1993324395, 0, 107380774),
//arrayListOf<Long>(1523978622, 465294601, 176895013),
//arrayListOf<Long>(106862826, 2588570007, 102241079),
//arrayListOf<Long>(2683278944, 864040264, 58789004),
//arrayListOf<Long>(4239553927, 4236813702, 55413369),
//arrayListOf<Long>(1097831573, 1081646455, 19946665),
//arrayListOf<Long>(524293914, 1101593120, 28493371),
//arrayListOf<Long>(336385104, 2833510990, 187908810),
//arrayListOf<Long>(552787285, 3197227470, 31933095),
//arrayListOf<Long>(845970820, 2690811086, 142699904),
//arrayListOf<Long>(988670724, 1388136917, 82436785),
//arrayListOf<Long>(1452409475, 838257601, 25782663),
//arrayListOf<Long>(3092138363, 409742466, 55552135),
//arrayListOf<Long>(584720380, 2416793325, 134492301),
//arrayListOf<Long>(4171950577, 3450098653, 23185575),)
//
//val lightToTemperatureMap = arrayListOf(
//arrayListOf<Long>(2906633798, 3843376160, 451591136),
//arrayListOf<Long>(1332454428, 1190958320, 69004583),
//arrayListOf<Long>(1837712164, 0, 353313230),
//arrayListOf<Long>(494809338, 353313230, 376619264),
//arrayListOf<Long>(871428602, 729932494, 461025826),
//arrayListOf<Long>(1401459011, 1754772241, 373416033),
//arrayListOf<Long>(3976747173, 3375456648, 91164221),
//arrayListOf<Long>(3495346659, 3466620869, 376755291),
//arrayListOf<Long>(0, 1259962903, 494809338),
//arrayListOf<Long>(2608238358, 2459541635, 298395440),
//arrayListOf<Long>(3907558614, 2984992977, 69188559),
//arrayListOf<Long>(3872101950, 2286963246, 35456664),
//arrayListOf<Long>(4067911394, 2757937075, 227055902),
//arrayListOf<Long>(3358224934, 2322419910, 137121725),
//arrayListOf<Long>(2286963246, 3054181536, 321275112),
//arrayListOf<Long>(1774875044, 2128188274, 62837120),)
//
//val temperatureToHumidityMap = arrayListOf(
//arrayListOf<Long>(3966168141, 3406025946, 214996780),
//arrayListOf<Long>(4181164921, 3292223571, 113802375),
//arrayListOf<Long>(1493139015, 1471031672, 367564898),
//arrayListOf<Long>(1423475871, 1838596570, 69663144),
//arrayListOf<Long>(0, 479293006, 226560784),
//arrayListOf<Long>(2500785470, 2859072453, 433151118),
//arrayListOf<Long>(3197453551, 2500785470, 96923792),
//arrayListOf<Long>(758446483, 1237739489, 233292183),
//arrayListOf<Long>(991738666, 0, 278789291),
//arrayListOf<Long>(3555740534, 3884539689, 410427607),
//arrayListOf<Long>(3294377343, 2597709262, 261363191),
//arrayListOf<Long>(226560784, 705853790, 531885699),
//arrayListOf<Long>(1860703913, 305742584, 20602508),
//arrayListOf<Long>(2933936588, 3621022726, 263516963),
//arrayListOf<Long>(1881306421, 278789291, 26953293),
//arrayListOf<Long>(1270527957, 326345092, 152947914),)
//
//val humidityToLocationMap = arrayListOf(
//arrayListOf<Long>(848612454, 2250862530, 61410922),
//arrayListOf<Long>(910023376, 3689675651, 35197452),
//arrayListOf<Long>(3724873103, 3865027106, 240221283),
//arrayListOf<Long>(483883727, 3324946924, 364728727),
//arrayListOf<Long>(0, 1766978803, 483883727),
//arrayListOf<Long>(1957894300, 561533, 922927950),
//arrayListOf<Long>(945220828, 2590144784, 734802140),
//arrayListOf<Long>(2880822250, 0, 561533),
//arrayListOf<Long>(3447014853, 1489120553, 277858250),
//arrayListOf<Long>(2881383783, 923489483, 565631070),
//arrayListOf<Long>(3965094386, 3724873103, 140154003),
//arrayListOf<Long>(1680022968, 2312273452, 277871332),)
//
//val sampleMaps = arrayListOf(
//    seedToSoilMap,
//    soilToFFertilizerMap,
//    fertilizerToWaterMap,
//    waterToLiightMap,
//    lightToTemperatureMap,
//    temperatureToHumidityMap,
//    humidityToLocationMap,
//)

