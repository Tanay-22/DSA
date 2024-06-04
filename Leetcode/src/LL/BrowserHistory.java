package LL;

public class BrowserHistory
{
    class Page
    {
        private String url;
        private Page prev;
        private Page next;

        Page(String url)
        {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    private Page homePage;
    private Page currentPage;
    private int currentPosition;
    public BrowserHistory(String homepage)
    {
        this.homePage = new Page(homepage);
        this.currentPage = homePage;
        currentPosition = 1;
    }

    public void visit(String url)
    {
        Page newPage = new Page(url);
        currentPage.next = newPage;
        newPage.prev = currentPage;
        currentPage = newPage;
        currentPosition++;
    }

    public String back(int steps)
    {
        if(steps >= currentPosition)
        {
            currentPosition = 1;
            currentPage = homePage;
            return currentPage.url;
        }

        while (steps-- > 0)
        {
            currentPosition--;
            currentPage = currentPage.prev;
        }
        return currentPage.url;
    }

    public String forward(int steps)
    {
        while (steps-- > 0)
        {
            if(currentPage.next != null)
            {
                currentPosition++;
                currentPage = currentPage.next;
            }
            else
                break;
        }
        return currentPage.url;
    }
}
