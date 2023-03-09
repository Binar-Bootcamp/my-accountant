package com.binaracademy.myaccountant.ui.laundry.model

import com.binaracademy.myaccountant.ui.laundry.mode.LaundryModel

object LaundryObj {
    private val laundries = arrayOf(
        "Tosca Laundry",
        "ZAF Laundry",
        "Ayundhira Laundry",
        "Kentjono Laundry",
        "Orchid Laundry",
        "Bolt-Z Laundry",
        "Java Laundry",
        "Djoeragan laundry",
        "Suci Laundry"
    )

    private val prices = arrayOf(
        5000,
        7500,
        10000,
        4500,
        6000,
        8000,
        12000,
        8500,
        11500
    )

    private val urls = arrayOf(
        "https://lh3.googleusercontent.com/p/AF1QipM-M9_VsLRgM6ghiZsEODyGWQo91jAf3doODiQC=w768-h768-n-o-v1",
        "https://lh5.googleusercontent.com/p/AF1QipO4_DvTVQcr6AaAh330p33Gf6tyBsut_F-zXpIU=w408-h544-k-no",
        "https://lh3.googleusercontent.com/p/AF1QipOHBO5Q0PX7FVkHsKX2yb2smMAORCwNDzHjUzTM=s1280-p-no-v1",
        "https://scontent-sin6-2.xx.fbcdn.net/v/t1.6435-9/131411788_102435588429938_4412631668504477959_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=174925&_nc_eui2=AeHOAMug1BZIF7KtSZoXrJRCZRWwX6XBRfBlFbBfpcFF8NKjhewj3VrqbJg7o4aRMfZpBS0CsvNv5yeDak2KA4up&_nc_ohc=O9oSk_mm5NcAX9NoFtb&_nc_ht=scontent-sin6-2.xx&oh=00_AfDTfFZ0eIeK1m8wE7ERdGFdCssidof-3XNvsgv8XqDVOw&oe=642FF704",
        "https://scontent-sin6-1.xx.fbcdn.net/v/t31.18172-8/14362695_1357430750952344_3850357446466100235_o.jpg?_nc_cat=107&ccb=1-7&_nc_sid=9267fe&_nc_eui2=AeFtXptLLUR6k3HlWWRw5oVAs5wEwwcVVBqznATDBxVUGnEW5snLYJedEzqKkzv7PBVcOUlEJpt9ZQjHm9iAVKS_&_nc_ohc=9gkj1MxeQrwAX_5lggv&_nc_ht=scontent-sin6-1.xx&oh=00_AfDzOk1X2EJyAJcpshFBjGhyCrSkG6s8ykXNXgCUv3RkTA&oe=64301270",
        "https://scontent.fsub8-1.fna.fbcdn.net/v/t39.30808-6/302247510_480731577399611_5627842957929060438_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeEN_jrucuRyL0OmLWF2linP_RiOQXWtMAf9GI5Bda0wBxbqSkZmqcQFjQh3upaLTkkmq4vQY_BKeUKRUaJcrf_y&_nc_ohc=xbVirt7pUL0AX99IvCF&_nc_ht=scontent.fsub8-1.fna&oh=00_AfC5TN3bcLpXlDJQc4iwr3Okpk-iUsJOI3C8XXwcydPKsQ&oe=640CE050",
        "https://lh5.googleusercontent.com/p/AF1QipMqGKvjXIqbSoedkym68Vr7gMd50KdOxS0opT6D=w300-h300-k-no-p",
        "https://lh3.googleusercontent.com/p/AF1QipNCXihtbirpzA4fmSovzmBRDDh8e8_zRpYhQ8za=w1080-h608-p-no-v0",
        "https://lh3.googleusercontent.com/p/AF1QipOLcKNXgIbzIJ0f3hh-Cq6M3dkrOiFZooltql_v=s1280-p-no-v1"
    )

    private val addresses = arrayOf(
        "Jl. Dewi Sekardadu RT.03 RW.02 , Perum Giri Asri, Ngargosari, Kebomas, Karangsong, Tlogopatut",
        "Samping Kantor, Jl. Sunan Giri No.53, Kebomas, Kawisanyar",
        "Jl. R.A. Kartini Gg. ⅩⅩ No.14, Injen Barat, Sidomoro",
        "Jl. Dr. Soetomo No.58, Trate, Tlogopatut",
        "Jl. Usman Sadar No.173, Ngipik, Karangpoh",
        "Jl. Usman Sadar No.224, Ngipik, Karangpoh",
        "Jl. Panglima Sudirman, Sumberrejo, Pulopancikan",
        "Jl. Kapten Dulasim No.57, Jegong, Singosari",
        "Jl. Panglima Sudirman No.162, Kramatandap, Sidomoro"
    )

    val listData : ArrayList<LaundryModel>
        get() {
            val list = arrayListOf<LaundryModel>()
            for (detail in laundries.indices){
                val laundryModel = LaundryModel(urls[detail], laundries[detail],
                    addresses[detail], prices[detail])
                list.add(laundryModel)
            }
            return list
    }
}