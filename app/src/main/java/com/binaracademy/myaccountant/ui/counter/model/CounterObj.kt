package com.binaracademy.myaccountant.ui.counter.model

object CounterObj {
	private val namas = arrayOf(
		"Mendoan Istana" ,
		"Ketoprak pak Toro" ,
		"Bakso malang Joko" ,
		"Gorengan Serba Gopek" ,
		"Tahu Genjrot Pak Supri" ,
		"Kembar Parfume" ,
		"Rumah Parfume" ,
		"Fome Ponsel" ,
		"Cheese Tea" ,
		"Susu Mbok Darmi"
	
	)
	
	private val prices = arrayOf(
		2000 ,
		12000 ,
		13000 ,
		500 ,
		5000 ,
		20000 ,
		25000 ,
		50000 ,
		15000,
		30000
	
	)
	
	private val urls = arrayOf(
		"https://www.franchiseglobal.com/images/posts/2020/09/08/mendoan-istana.JPG" ,
		"https://data.spektakel.id/articles/Ketoprak%20Toro%201.jpg" ,
		"https://cdns.klimg.com/merdeka.com/i/w/news/2016/11/10/777169/670x335/potensi-usaha-menjanjikan-miwon-sebar-300-gerobak-bakso.jpg" ,
		"https://2.bp.blogspot.com/-WvAQ2pw_tMg/VwIvaGbwLNI/AAAAAAAAEbQ/pOr24kTzfcACVaQzGF234uPUeNKXqrX-A/s1600/Bisnis%2BGorengan.jpg" ,
		"https://akcdn.detik.net.id/community/media/visual/2020/08/08/rekomendasi-tahu-gejrot-pedas.jpeg?w=700&q=90" ,
		"https://www.liputanbmr.com/wp-content/uploads/2021/10/IMG_20211028_215048-620x330.jpg" ,
		"https://scontent-sin6-2.xx.fbcdn.net/v/t1.18169-9/167008_10150089110892360_5193948_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=cdbe9c&_nc_ohc=MJVITU7Uq8QAX9Vsx5e&_nc_ht=scontent-sin6-2.xx&oh=00_AfAUieRb3kYC_NLByoNFRKtSPKOyHH5IXnBlZrXbexyjgw&oe=64298B24" ,
		"https://cdn-2.tstatic.net/lampung/foto/bank/images/pemuda-di-tuba-lampung-raup-ratusan-juta-per-bulan.jpg" ,
		"https://image.cermati.com/q_70/svzpy2gnrrkdqzxb0que.webp" ,
		"https://garuda.industry.co.id/uploads/berita/detail/47934.jpg"
	)
	private val locations = arrayOf(
		"Jl. Kemerdekaan, no.22" ,
		"Jl. Gang AB" ,
		"Jl. Assirot 22" ,
		"Jl. Kesehatan. No.33" ,
		"Jl. cidodol. No.33" ,
		"jl. H.Gareng. No.45A " ,
		"Jl. Kebayoran lama" ,
		"jl. H.Gareng. No.45A" ,
		"Mall Gandaria City" ,
		"Mall Gandaria City blok.33A"
	
	)
	
	
	val listData : ArrayList<ModelItem>
		get() {
			val list = arrayListOf<ModelItem>()
			for (detail in namas.indices) {
				val modelItem = ModelItem(urls[detail] , namas[detail] , locations[detail], prices[detail])
				list.add(modelItem)
			}
			return list
		}
	
	
}