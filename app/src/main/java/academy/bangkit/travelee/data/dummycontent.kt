package academy.bangkit.travelee.data

import academy.bangkit.travelee.model.ContentModel


object GuidelineData {
    val data = listOf(
        ContentModel(
            Id = 1,
            Title = "Pahami dan Hormati Budaya Lokal",
            Content = """
                - Belajar tentang budaya, adat istiadat, dan tradisi masyarakat lokal sebelum berkunjung.
                - Hormati kebiasaan dan aturan komunikasi yang berlaku di desa wisata.
                - Hindari perilaku yang dianggap mengganggu atau merugikan budaya lokal, seperti fotografi yang tidak pantas atau mengambil barang-barang tanpa izin.
            """.trimIndent()
        ),
        ContentModel(
            Id = 2,
            Title = "Jaga Kebersihan Lingkungan",
            Content = """
                - Bawa kantong sampah sendiri dan buang sampah pada tempatnya.
                - Ikuti sistem pengelolaan sampah yang ada di desa wisata.
                - Gunakan fasilitas toilet yang disediakan dan jangan buang sampah di sekitar area wisata.
            """.trimIndent()
        ),
        ContentModel(
            Id = 3,
            Title = "Gunakan Transportasi Berkelanjutan",
            Content = """
                - Prioritaskan penggunaan transportasi umum, bersepeda, atau berjalan kaki untuk menjaga kebersihan udara dan mengurangi emisi gas rumah kaca.
                - Jika menggunakan kendaraan pribadi, berbagilah perjalanan dengan pengunjung lain untuk mengurangi lalu lintas dan dampak lingkungan.
            """.trimIndent()
        ),
        ContentModel(
            Id = 4,
            Title = "Dukung Ekonomi Lokal",
            Content = """
               - Belanjalah di toko atau warung lokal untuk mendukung perekonomian masyarakat desa wisata.
               - Pilihlah homestay atau akomodasi yang dikelola oleh penduduk setempat untuk memberikan manfaat ekonomi langsung kepada mereka.
               - Dukung usaha kerajinan tangan lokal dengan membeli produk-produk mereka sebagai oleh-oleh.
            """.trimIndent()
        ),
        ContentModel(
            Id = 5,
            Title = "Hargai Kesejahteraan Hewan dan Alam",
            Content = """
                - Hindari mendukung atraksi wisata yang melibatkan penangkapan atau eksploitasi hewan.
                - Jangan memberi makan atau mengganggu satwa liar di sekitar desa wisata.
                - Hindari merusak atau mengambil flora dan fauna yang dilindungi di daerah wisata.
            """.trimIndent()
        ),
    )
}