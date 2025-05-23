package year2023.day07

fun main(args: Array<String>) {
    val input = realInput07
//  val result = ArrayList<Triple<String, Int, Kind>>()
    val parsed = arrayListOf<Hand>()

    for (line in input) {
        val hand = Hand()
        hand.cards = line.split(" ")[0]
        hand.bid = line.split(" ")[1].toInt()
        parsed.add(hand)
    }

//        parsed.forEach { println(it) }
//        println("------------------------")


  for (hand in parsed) {

            var kind = Kind.Unknown
            for (letter in order2) {
                    val occurrencesMap = mutableMapOf<Char, Int>()
                    var tempCards = hand.cards.replace("J", letter)
                    for (c in tempCards) {
                        occurrencesMap.putIfAbsent(c, 0)
                        occurrencesMap[c] = occurrencesMap[c]!! + 1
                    }
                    var kindLocal =
                    if (occurrencesMap.keys.size == 1 && occurrencesMap.values.contains(5)) {
                        Kind.Five_of_a_kind
                    } else if (occurrencesMap.keys.size == 2 && occurrencesMap.values.contains(4) && occurrencesMap.values.contains(1)) {
                        Kind.Four_of_a_kind
                    } else if (occurrencesMap.keys.size == 2 && occurrencesMap.values.contains(3) && occurrencesMap.values.contains(2)) {
                                              Kind.Full_house
                    } else if (occurrencesMap.keys.size == 3 && occurrencesMap.values.contains(3) && occurrencesMap.values.contains(1)) {
                                              Kind.Three_of_a_kind // ++
                    } else if (occurrencesMap.keys.size == 3 && occurrencesMap.values.contains(2) && occurrencesMap.values.contains(1)) {
                                              Kind.Two_pair // ++
                    } else if (occurrencesMap.keys.size == 4 && occurrencesMap.values.contains(2) && occurrencesMap.values.contains(1)) {
                                              Kind.One_pair // ++
                   } else if  (occurrencesMap.keys.size == 5 && occurrencesMap.values.contains(1)) {
                                                                   Kind.High_card
                                        } else Kind.Unknown
                    if (kindLocal.rank <= kind.rank) kind = kindLocal
//                    println("$hand - $tempCards - $kindLocal")
            }
            hand.kind = kind



  }


println("==============")
  for (hand in parsed) {
//      println(hand)
      if (hand.kind == Kind.Unknown) {
          throw IllegalStateException()
      }
  }

//  val existingRanks = parsed.map { it.kind }.sortedBy {
//      it.rank }.toSet().reversed()
//     println(existingRanks)
//
//     existingRanks.forEachIndexed { rank, kind ->
//         parsed.filter { it.kind == kind  }.forEach { it.rank = rank +1 }
//      }


//       parsed.sortWith(comparator = comparator2)
        parsed.sortedWith(comparator2).forEachIndexed{ rank, hand ->
            hand.rank = parsed.size - rank
        }
        parsed.sortedBy { it.rank }.forEach { println(it) }
        println("------------------------")
        println("result")
        var result = 0L
        parsed.forEach { result = result + it.rank*it.bid }
        println(result)
//        println( parsed.map { it.rank*it.bid }.sum())
//        println( parsed.size)
//        println(parsed.map { it.cards }.toSet().size)

}

object comparator2: Comparator<Hand> {
    override fun compare(o1: Hand, o2: Hand): Int {
//        println("$o1 - $o2")
//        println(if (o1.kind.rank > o2.kind.rank)
//                            1
//                        else -1)
        if (o1.kind.rank > o2.kind.rank) return 1
        else if (o1.kind.rank < o2.kind.rank) return -1
        else {
            for (i in 0..5) {
                if (order2.indexOf(o1.cards.split("")[i]) > order2.indexOf(o2.cards.split("")[i])) return 1
                else if (order2.indexOf(o1.cards.split("")[i]) < order2.indexOf(o2.cards.split("")[i])) return -1
            }
        }
        println("$o1 - $o2")
        throw IllegalStateException()
        return 0
    }


}

//1st 248618050
//2nd 248750248 - correct answer for real input

 val order2 = arrayListOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J")
//
//val sampleInput07 = arrayListOf(
//"32T3K 765",
//"T55J5 684",
//"KK677 28",
//"KTJJT 220",
//"QQQJA 483",
//)
//val realInput07 = arrayListOf(
//"757T6 637",
//"TTT2T 589",
//"4567T 670",
//"J77QQ 261",
//"Q63KQ 179",
//"T87KQ 969",
//"Q2Q62 401",
//"333Q3 241",
//"K5A64 839",
//"3JKJ4 152",
//"Q8332 122",
//"59K7J 32",
//"A6666 977",
//"Q76JJ 522",
//"Q8992 704",
//"58555 402",
//"79987 147",
//"T9K27 364",
//"83J38 74",
//"48442 128",
//"789J8 223",
//"4QT43 625",
//"T5557 525",
//"222J9 329",
//"6AQ65 386",
//"5J924 255",
//"6326T 848",
//"9Q398 108",
//"8ATTT 150",
//"6JT6A 160",
//"32T9A 587",
//"9T874 49",
//"66686 669",
//"26666 887",
//"89K46 768",
//"88T85 846",
//"JTA9T 582",
//"JK494 722",
//"K5855 437",
//"T4J8T 851",
//"J3J99 299",
//"639KK 436",
//"K4JK3 219",
//"99797 211",
//"4A797 18",
//"KT6JT 31",
//"237T5 711",
//"95599 491",
//"946K9 941",
//"AKKQ7 621",
//"Q24T8 460",
//"66J3J 756",
//"3QK24 760",
//"69A5K 280",
//"Q42A4 20",
//"43939 931",
//"567JT 506",
//"29992 177",
//"A9TJA 62",
//"K666A 440",
//"AA8AA 707",
//"9K9K9 125",
//"K6J22 442",
//"59T28 53",
//"4K5T4 238",
//"JK6JK 636",
//"4922K 120",
//"5A837 814",
//"Q55J8 627",
//"5QQ5J 925",
//"JA8A8 39",
//"4J482 483",
//"AA323 302",
//"95439 315",
//"T5T45 724",
//"64J44 679",
//"JAA93 618",
//"T69T5 623",
//"47744 630",
//"A77A7 78",
//"88A8A 469",
//"9A357 828",
//"72A68 106",
//"6AQT9 278",
//"77557 447",
//"757J7 899",
//"7T264 40",
//"7K77K 264",
//"JQ98Q 256",
//"A7377 50",
//"443TK 493",
//"99898 205",
//"A8AAK 93",
//"A525A 408",
//"JQKKQ 526",
//"64J74 651",
//"K2KTK 661",
//"9999J 616",
//"T9TJK 486",
//"Q9J9Q 176",
//"8T838 895",
//"2K222 877",
//"399J9 251",
//"AA7KT 936",
//"T494K 249",
//"AQAAQ 90",
//"5TTT5 750",
//"48843 689",
//"37Q7Q 823",
//"77722 236",
//"9K482 390",
//"AQQ76 712",
//"2QQQ9 270",
//"QKQKQ 320",
//"35Q33 396",
//"T52T2 967",
//"3Q3QQ 2",
//"KT57K 709",
//"8AT8A 672",
//"A7799 59",
//"TT888 880",
//"5TTT2 648",
//"3A62J 654",
//"22545 960",
//"34T9Q 757",
//"74477 311",
//"AQAJ5 989",
//"3K366 138",
//"4AA44 987",
//"85458 206",
//"2K322 259",
//"2822J 660",
//"JJ73K 371",
//"44Q4Q 954",
//"7A3TA 529",
//"Q794J 418",
//"84488 742",
//"7J77J 6",
//"J4459 165",
//"JQK9K 345",
//"22ATT 154",
//"88778 729",
//"97497 600",
//"55AQQ 42",
//"68888 172",
//"69JJQ 970",
//"85A38 746",
//"57J55 189",
//"74977 114",
//"Q3389 605",
//"37883 499",
//"QQ3QJ 734",
//"955K9 590",
//"3T5J6 488",
//"7227J 296",
//"3K63K 753",
//"QQJJJ 260",
//"K4867 766",
//"J4TKQ 307",
//"TJAJ2 514",
//"99AQ5 662",
//"K2A2T 774",
//"29242 169",
//"T99J9 393",
//"T5QQ3 213",
//"K4K4K 567",
//"JATQ6 520",
//"93J78 463",
//"J43JJ 56",
//"2J222 7",
//"9T374 638",
//"76695 694",
//"3J748 641",
//"AA4A4 549",
//"4444J 412",
//"J8Q99 309",
//"99695 680",
//"7Q7QQ 112",
//"KK2KQ 728",
//"5746A 971",
//"JA4J4 952",
//"599Q9 391",
//"74574 457",
//"5K477 642",
//"KK899 751",
//"9JK25 12",
//"TKK9J 257",
//"35333 235",
//"7KKKK 407",
//"22442 141",
//"KJ55K 565",
//"JQQQQ 933",
//"22323 888",
//"AT2Q4 560",
//"5T5T5 357",
//"38KA5 431",
//"86668 869",
//"QJ44Q 715",
//"TAT8A 603",
//"6J6J6 573",
//"KJKK5 174",
//"7T6TT 900",
//"36353 350",
//"545J5 508",
//"39336 476",
//"7Q977 535",
//"99A99 470",
//"8898J 234",
//"QJ9QQ 304",
//"J5892 13",
//"K8427 765",
//"J2555 541",
//"6257Q 862",
//"63529 272",
//"3KK55 339",
//"68A2K 867",
//"4JJJT 949",
//"2A2Q7 510",
//"6KAKK 721",
//"QAAQJ 703",
//"3K343 875",
//"A25A6 564",
//"Q9977 873",
//"99222 795",
//"47772 30",
//"QT4K5 864",
//"K5978 216",
//"QQQ4Q 76",
//"K3KJJ 419",
//"KKKJK 305",
//"57272 594",
//"TTT87 341",
//"Q2AQJ 335",
//"5Q23Q 444",
//"77774 972",
//"J8Q44 700",
//"JJ444 314",
//"A428J 183",
//"TK725 290",
//"332T3 929",
//"TQ5J2 321",
//"797K9 163",
//"JT222 983",
//"A666A 441",
//"QTQQ7 790",
//"AKKAT 400",
//"J9JQA 596",
//"7K93A 443",
//"3Q869 904",
//"5TTQ5 609",
//"KTK8J 33",
//"9A338 131",
//"TK22T 650",
//"5J25T 732",
//"A5422 190",
//"KK68K 719",
//"TQ7KT 367",
//"92438 217",
//"89895 3",
//"8J8QQ 1",
//"JJJJJ 613",
//"6366T 584",
//"TQ4QQ 745",
//"6KJJQ 449",
//"JTT88 502",
//"736QQ 847",
//"687Q3 570",
//"K8TA4 807",
//"TATTA 298",
//"TTKTT 186",
//"47KKK 597",
//"TAAJT 145",
//"QQ6TQ 926",
//"739Q9 519",
//"43895 922",
//"795TQ 389",
//"264AJ 996",
//"A5Q4T 166",
//"77JQJ 914",
//"Q5T99 720",
//"67Q66 45",
//"8J499 859",
//"393K3 501",
//"3TQ4A 63",
//"AA8AJ 254",
//"A343Q 375",
//"T6T6J 965",
//"Q594A 838",
//"4T29Q 101",
//"3QJJ3 267",
//"K3J3K 958",
//"78K88 126",
//"J42KJ 824",
//"78T53 195",
//"44Q9Q 676",
//"43434 592",
//"6J524 918",
//"KQK85 88",
//"Q66Q6 671",
//"45Q34 207",
//"J95KK 810",
//"9J997 588",
//"42944 882",
//"JQQQJ 871",
//"98Q9Q 490",
//"AAJA5 279",
//"5A87T 285",
//"AAAJ2 252",
//"77KA3 932",
//"7J68K 699",
//"A3Q33 944",
//"8634T 303",
//"5T555 373",
//"27865 752",
//"94999 27",
//"A7JJJ 579",
//"22J26 577",
//"99944 659",
//"444T4 903",
//"66Q9Q 340",
//"QQJQ7 404",
//"K29JJ 487",
//"6QQ64 4",
//"88A88 158",
//"444Q4 845",
//"7JT29 657",
//"92KQ5 889",
//"36JJQ 366",
//"Q4866 865",
//"4433J 376",
//"Q98TK 975",
//"J9734 192",
//"9JAAA 817",
//"22779 674",
//"6Q877 917",
//"77773 553",
//"Q9899 28",
//"T4A32 198",
//"64448 11",
//"92T8Q 181",
//"36233 123",
//"6KK46 44",
//"23273 829",
//"8AJ7Q 83",
//"64293 966",
//"Q6666 295",
//"3T2J3 812",
//"63633 962",
//"37T2A 355",
//"KQ565 726",
//"3TQJT 149",
//"999T9 681",
//"7AKK5 66",
//"44T4T 9",
//"2TTT2 702",
//"KK82K 313",
//"TTJT7 538",
//"8JKAA 548",
//"JJ222 593",
//"K3JKK 855",
//"23KQ6 432",
//"33745 531",
//"6393J 324",
//"75885 982",
//"656KK 91",
//"A7JKA 383",
//"73979 919",
//"AAKA7 948",
//"56565 536",
//"8A345 119",
//"3QT37 911",
//"5T5T8 504",
//"96Q4T 552",
//"KAJKK 271",
//"5T57T 148",
//"3K6Q7 433",
//"3K98K 316",
//"77TT6 837",
//"Q4QQJ 178",
//"7T7TT 602",
//"75AA5 527",
//"42K2K 992",
//"53JAA 910",
//"AJ6K6 629",
//"38333 769",
//"KK668 533",
//"TK77T 439",
//"67Q57 598",
//"A2AA2 416",
//"888QA 156",
//"Q2J6J 951",
//"32568 673",
//"57377 569",
//"9T954 47",
//"65523 292",
//"46754 98",
//"55367 248",
//"Q2JJ3 532",
//"6826K 731",
//"44227 231",
//"92223 95",
//"KQQ2Q 701",
//"QQ9A5 262",
//"T42T4 534",
//"8T4Q4 868",
//"4TTJK 139",
//"46589 934",
//"5252K 717",
//"2TQT2 118",
//"5JT82 414",
//"5T4T3 852",
//"599QQ 913",
//"67765 51",
//"67777 968",
//"K2K22 682",
//"A49J7 453",
//"232J4 388",
//"23585 892",
//"92988 980",
//"JQ9T9 323",
//"T3T33 545",
//"2QKT5 94",
//"K859Q 777",
//"TQJ3K 758",
//"QT8J3 110",
//"A3AA2 326",
//"8TJ88 187",
//"8K8K8 22",
//"99J89 617",
//"J4KA4 844",
//"A2A22 863",
//"46Q75 981",
//"Q55AA 666",
//"9J555 737",
//"92295 599",
//"J7482 785",
//"AA77A 772",
//"Q8QQJ 872",
//"777A7 467",
//"444AT 542",
//"9K9AA 834",
//"27222 784",
//"5357T 878",
//"4T2T3 343",
//"A8996 8",
//"73A3A 19",
//"AA592 151",
//"3JT48 38",
//"5QQ5Q 286",
//"KK573 793",
//"QQQ2Q 497",
//"J6T65 274",
//"8K56T 233",
//"2299J 735",
//"AA47Q 413",
//"529A3 344",
//"K5955 706",
//"77Q7Q 245",
//"66J44 116",
//"999A8 804",
//"75J8A 434",
//"57525 111",
//"4K4TK 107",
//"98A88 781",
//"QT896 767",
//"J6666 691",
//"AJT9Q 764",
//"Q66TT 991",
//"QTTQQ 96",
//"77679 132",
//"59J2Q 310",
//"8T886 803",
//"AQ379 17",
//"4TQTT 301",
//"2J882 606",
//"848TK 15",
//"TQQ7A 927",
//"QQ5TA 964",
//"222TT 759",
//"444K4 399",
//"J6356 204",
//"59549 334",
//"AJTT3 780",
//"97J97 137",
//"AAJAA 782",
//"5T37T 820",
//"5T9TT 663",
//"4A969 406",
//"6Q595 164",
//"66J26 220",
//"965T3 41",
//"44949 716",
//"A5555 771",
//"3366J 586",
//"QJ3TQ 48",
//"94464 336",
//"7KQ53 456",
//"75JA9 718",
//"3Q3TQ 668",
//"589A4 369",
//"9K437 228",
//"Q5347 438",
//"9J758 492",
//"KTA7K 747",
//"Q333K 129",
//"3Q294 370",
//"2K7A7 832",
//"4445J 561",
//"63333 513",
//"T3J47 656",
//"74444 612",
//"KK69K 325",
//"55575 557",
//"8JK82 622",
//"42KT7 896",
//"JTAJA 422",
//"K7843 318",
//"5AA59 273",
//"J3373 595",
//"TQJ88 244",
//"TK637 884",
//"6J496 485",
//"TTQT2 997",
//"679J6 489",
//"A692K 909",
//"2J834 854",
//"69A35 201",
//"77KJ6 171",
//"4QKT4 52",
//"QJ88A 349",
//"5A3QK 692",
//"69943 221",
//"9Q3QQ 374",
//"92J78 686",
//"3KA7Q 224",
//"33898 794",
//"44666 104",
//"TK23A 841",
//"22327 97",
//"39377 842",
//"35335 836",
//"KA383 604",
//"87J53 450",
//"5KQ88 723",
//"TJTQ7 54",
//"33343 876",
//"77667 539",
//"K9K4Q 558",
//"Q737J 505",
//"88223 498",
//"278A3 827",
//"AJAJA 426",
//"7KKK7 976",
//"76J94 461",
//"5666J 959",
//"A872Q 879",
//"999AJ 347",
//"66888 749",
//"QJQQT 544",
//"9666Q 109",
//"KJ72T 755",
//"4J829 620",
//"22888 372",
//"JJTT9 853",
//"QJQT9 105",
//"Q32T4 687",
//"5T5KJ 800",
//"JJ464 494",
//"JT565 819",
//"A4635 639",
//"79475 571",
//"TATTT 822",
//"98656 559",
//"62276 268",
//"9TK4A 826",
//"J2K55 275",
//"4QQ4K 84",
//"73878 354",
//"82593 825",
//"J5TT5 902",
//"22626 801",
//"K63Q3 175",
//"4Q8TT 874",
//"9TT8T 363",
//"K2K5K 25",
//"22525 986",
//"33443 776",
//"977A7 675",
//"TT9TT 332",
//"4A6AA 23",
//"JAAJ8 802",
//"Q99J9 649",
//"33J99 956",
//"4K74T 495",
//"K38Q6 479",
//"245QK 521",
//"K25K5 395",
//"QAAAA 16",
//"QQQKQ 46",
//"J8488 300",
//"3JTTT 835",
//"66993 478",
//"44356 554",
//"93329 883",
//"55545 167",
//"39999 477",
//"29999 392",
//"J3AJ5 738",
//"KKK33 87",
//"TTTT8 144",
//"A33AA 831",
//"52555 287",
//"3TT3T 658",
//"5KK55 199",
//"8J6AA 237",
//"J5TJ8 619",
//"JJAT3 218",
//"A7A62 786",
//"5KKK5 379",
//"9A8AA 247",
//"KKA5K 857",
//"56555 736",
//"8637A 197",
//"7AA25 365",
//"9K474 516",
//"74944 792",
//"K66K6 890",
//"7222A 912",
//"JA55A 67",
//"768TJ 284",
//"26222 240",
//"T7K5Q 833",
//"76357 162",
//"T2KJT 632",
//"JAQQQ 468",
//"6T769 984",
//"88J62 773",
//"393Q3 405",
//"KQKJ4 79",
//"422A3 640",
//"743KT 924",
//"TA5KA 420",
//"4A7QJ 950",
//"9297J 282",
//"A33JA 928",
//"45296 317",
//"T7279 979",
//"J3AAA 555",
//"A75KQ 92",
//"5J662 387",
//"K999Q 741",
//"TT4TT 466",
//"T8JK5 998",
//"AQA83 5",
//"42374 696",
//"A23QT 893",
//"KQ49A 362",
//"4KKJK 427",
//"J7K7K 86",
//"853AJ 830",
//"A888T 184",
//"K3KKK 957",
//"Q2425 474",
//"82822 923",
//"9JKKK 475",
//"JA6A9 540",
//"88577 645",
//"44443 653",
//"6Q444 518",
//"2833J 667",
//"AK685 194",
//"2783J 243",
//"T6JA5 543",
//"TA887 43",
//"47958 799",
//"9399T 955",
//"6AT23 276",
//"4AA88 610",
//"JA9K8 860",
//"QQ5QQ 743",
//"5J6Q4 698",
//"JJ56J 89",
//"AT9AA 563",
//"4JA44 464",
//"24444 938",
//"882K3 524",
//"4KJKT 646",
//"5T525 209",
//"T8TT8 870",
//"59555 75",
//"6KTQJ 840",
//"288Q2 68",
//"TT777 985",
//"9TTJ4 410",
//"JTJ36 818",
//"39Q37 953",
//"36T6T 10",
//"A8AJ7 193",
//"5599J 124",
//"AT8JA 961",
//"3J3T3 121",
//"9Q637 55",
//"66K88 170",
//"44449 159",
//"Q6688 65",
//"73AT7 117",
//"3K2AQ 103",
//"77878 338",
//"3669A 378",
//"JA552 377",
//"TTJTT 342",
//"JA27K 451",
//"5JKKJ 849",
//"2998J 465",
//"A4Q4A 359",
//"J3393 210",
//"7KA83 142",
//"4T3KJ 417",
//"27QQQ 291",
//"Q4QQ4 288",
//"35ATJ 289",
//"7T292 787",
//"9966J 748",
//"A9A99 113",
//"2JK74 906",
//"T856Q 683",
//"8KKK7 77",
//"Q2727 385",
//"882TA 202",
//"AA298 655",
//"9TT92 945",
//"738K9 943",
//"KKKTT 61",
//"Q3T3T 821",
//"88T88 788",
//"57TTK 60",
//"T7QK9 994",
//"2Q3A3 523",
//"73377 226",
//"3T337 452",
//"JTJ94 306",
//"766JT 423",
//"Q555Q 269",
//"AJQAA 730",
//"75T5A 808",
//"Q6A7T 29",
//"KQKQT 551",
//"Q7T38 381",
//"J7567 283",
//"4ATA2 239",
//"333J3 935",
//"A232A 789",
//"44623 115",
//"KT77A 915",
//"4888K 230",
//"K398T 693",
//"8AAA8 102",
//"3KT3K 763",
//"27272 665",
//"52222 937",
//"96966 939",
//"AT4TT 222",
//"8JJTJ 294",
//"J33Q4 398",
//"J5886 140",
//"KT26Q 575",
//"68788 99",
//"6TKTT 999",
//"TJ823 861",
//"22246 293",
//"KQK7K 705",
//"6A3T8 739",
//"T4T6T 568",
//"A8373 225",
//"663J6 127",
//"5KA52 644",
//"66ATT 916",
//"88863 446",
//"84444 196",
//"99298 34",
//"3TAJ8 26",
//"3A746 633",
//"33838 481",
//"KJ66K 806",
//"T9577 566",
//"TAAJQ 677",
//"76875 473",
//"Q88Q8 448",
//"8995Q 454",
//"AJ766 35",
//"26A9J 635",
//"2TJ7A 229",
//"T2242 615",
//"QA3JT 214",
//"373A7 500",
//"5TT33 330",
//"63TQ7 990",
//"2522J 684",
//"99339 942",
//"KT63A 576",
//"93226 995",
//"86T5A 628",
//"9QAK8 312",
//"7J797 697",
//"T2TJT 797",
//"8Q28Q 963",
//"J45TK 168",
//"7777J 394",
//"33424 624",
//"A7A3A 72",
//"ATAKT 511",
//"6K4QA 778",
//"Q5Q4Q 974",
//"JQ333 988",
//"AA2J2 459",
//"TK68Q 556",
//"Q8888 462",
//"7J676 70",
//"4A72T 445",
//"QAQQA 901",
//"T6QQ5 608",
//"4K44Q 881",
//"2A33K 81",
//"74AK2 253",
//"6J6QQ 775",
//"2Q77J 509",
//"78K69 770",
//"2JAQK 537",
//"T66T6 482",
//"2K2KT 322",
//"3TT37 607",
//"ATAAT 744",
//"TTKK8 409",
//"22333 783",
//"44422 708",
//"JT39T 424",
//"79279 348",
//"QQQ9K 811",
//"JKTQ7 908",
//"8AA7A 580",
//"33K37 626",
//"27962 429",
//"8Q838 250",
//"JQ24J 153",
//"T5599 796",
//"23333 421",
//"8KJ8J 188",
//"T4544 815",
//"T8QT2 71",
//"A9J43 425",
//"4J4TT 740",
//"KJ73A 319",
//"2T6TT 327",
//"5KK7K 517",
//"A5AAA 581",
//"8868J 993",
//"T5A5T 856",
//"TQQ48 14",
//"ATTT6 346",
//"TQ3TQ 136",
//"6A9JJ 308",
//"9Q5QK 471",
//"J99KQ 161",
//"999Q9 130",
//"J9692 258",
//"64TT6 182",
//"Q2534 227",
//"97779 710",
//"8632Q 905",
//"56TQT 263",
//"55JJ5 173",
//"99799 100",
//"3Q489 185",
//"2Q2TJ 208",
//"99693 73",
//"5555J 191",
//"A4JA9 134",
//"27426 528",
//"5AKJK 215",
//"789AJ 921",
//"53435 885",
//"6626A 664",
//"K634T 384",
//"4388T 356",
//"JJ8JJ 36",
//"7QQ6Q 685",
//"43664 266",
//"Q4593 920",
//"88884 200",
//"244QJ 733",
//"99JJ9 82",
//"4474T 816",
//"64TA9 382",
//"42Q55 562",
//"J6646 337",
//"6TQTQ 688",
//"4J4JT 57",
//"3Q373 472",
//"25QJA 946",
//"99QKQ 64",
//"9T929 647",
//"3JQ83 591",
//"8JK86 265",
//"78Q37 331",
//"7A965 368",
//"T3T7Q 85",
//"58642 643",
//"2944Q 695",
//"8JJ88 411",
//"23K43 798",
//"63663 1000",
//"J895Q 973",
//"K9T3T 886",
//"TQJ98 351",
//"7KJ4T 480",
//"56566 761",
//"AAKAA 940",
//"5JTTJ 484",
//"Q45A4 80",
//"JJKKK 403",
//"55298 297",
//"4K4TT 547",
//"JQQ6Q 546",
//"89AJT 809",
//"J33J3 578",
//"8A5A8 246",
//"JA8KJ 21",
//"62426 203",
//"K43Q9 690",
//"J2Q85 813",
//"6AK27 727",
//"QQ8QQ 333",
//"6A9T3 435",
//"22242 805",
//"55K66 428",
//"K4K2K 894",
//"T7T44 37",
//"TQTJT 898",
//"AA2AA 978",
//"7AKA9 277",
//"5KA56 430",
//"99989 652",
//"4A8AQ 58",
//"5KJ55 843",
//"4KQ5A 550",
//"K976T 157",
//"T2222 24",
//"37733 358",
//"A9AQ5 631",
//"T2847 583",
//"27226 714",
//"92332 503",
//"94K4K 515",
//"JTTJT 507",
//"K8A89 135",
//"AT72T 530",
//"269J8 512",
//"59Q3T 69",
//"8822K 397",
//"KJQ42 455",
//"Q9T47 496",
//"62KAJ 907",
//"3KKK6 574",
//"4K7K8 713",
//"55AAK 930",
//"T9878 133",
//"2JTTQ 380",
//"79K87 611",
//"4K72K 212",
//"44643 180",
//"K5555 281",
//"K7276 458",
//"7224Q 634",
//"T9TT9 143",
//"A44JA 614",
//"4J5A6 754",
//"88J88 155",
//"A4938 866",
//"K22J2 762",
//"684KT 678",
//"99599 779",
//"T78J9 353",
//"A7A37 572",
//"9KKKK 601",
//"6T666 361",
//"4949J 328",
//"76784 791",
//"2T68T 850",
//"K4KKK 725",
//"37J6T 897",
//"A327A 146",
//"39J7A 858",
//"8TA4Q 415",
//"5T83K 352",
//"JJ99J 360",
//"8894T 242",
//"Q49QQ 232",
//"Q9QQQ 891",
//"6262A 585",
//"K2K2K 947",)
//
////val cardsAndBids = arrayListOf(
////Triple("32T3K", 765, Kind.Unknown),
////Triple("T55J5", 684, Kind.Unknown),
////Triple("KK677", 28, Kind.Unknown),
////Triple("KTJJT", 220, Kind.Unknown),
////Triple("QQQJA", 483, Kind.Unknown),
////)
//
//class Hand() {
//    var cards: String = ""
//    var bid: Int = 0
//    var kind = Kind.Unknown
//    var rank = 0
//
//    override fun toString(): String {
//return "Hand(cards='$cards', bid=$bid, kind=$kind, rank=$rank)"
//}
//
//}
//
//enum class Kind(val rank:Int) {
//
//Five_of_a_kind(0),
//Four_of_a_kind(1),
//Full_house(2),
//Three_of_a_kind(3),
//Two_pair(4),
//One_pair(5),
//High_card(6),
//Unknown(Int.MAX_VALUE)
//
//}