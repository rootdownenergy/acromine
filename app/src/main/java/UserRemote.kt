
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.google.gson.annotations.Expose

@JsonClass(generateAdapter = true)
data class UserRemote(
    @Json(name = "data")
    @Expose
    val `data`: Data?,
    @Json(name = "message")
    @Expose
    val message: String?, // User register successfully.
    @Json(name = "success")
    @Expose
    val success: Boolean? // true
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "name")
        @Expose
        val name: String?, // Tester 1
        @Json(name = "token")
        @Expose
        val token: String? // 2|mlBB5nazoEkunrklrsRDRU5S8VRnWxZnvY95HwZ6
    )
}