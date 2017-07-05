package viktoriia.testprovectus.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostModel() {
    }

    /**
     *
     * @param results
     * @param info
     */
    public PostModel(List<Result> results, Info info) {
        super();
        this.results = results;
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}