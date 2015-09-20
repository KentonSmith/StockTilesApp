# StockTilesApp

This is a simple app to keep track of a list of stock tickers.  Users can add or delete stock tickers that they want to follow via the homescreen menu which alters a SQLite Database underneath.  When the "list" button is pressed, a RecyclerView + CardView activity occurs, with each ticker in the user's database populating a cardview.  The information from the cardviews is obtained by scraping Yahoo finance's website (mobile version) using a Thread.  Each cardview can be flung left/right to be deleted.  Each cardview has an onClick handler that will open a webview with the actual Yahoo finance page, so that the user can see more detailed information.

![](https://s3.amazonaws.com/githubreadmepictures/Activity_Flow_Chart.png)
