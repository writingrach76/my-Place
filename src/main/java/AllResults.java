import java.util.ArrayList;

public class AllResults {
    private ArrayList<Result> _results = new ArrayList<Result>();

    public AllResults()
    {

    }


    public int findResult()
    {
        for(Result r: _results)
        {
            if(r.getResult())
            {

            }
        }
        return 0;
    }

    public String getResult(int i)
    {
        return _results.get(i).getResult();
    }



}
