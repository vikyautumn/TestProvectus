package viktoriia.testprovectus.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    @Expose
    private String seed;
    @SerializedName("results")
    @Expose
    private int results;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * No args constructor for use in serialization
     *
     */
    public Info() {
    }

    /**
     *
     * @param results
     * @param page
     * @param seed
     * @param version
     */
    public Info(String seed, int results, int page, String version) {
        super();
        this.seed = seed;
        this.results = results;
        this.page = page;
        this.version = version;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}