 <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Bodhi</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/bodhi/admin/home">Home</a></li>
            <li><a href="/bodhi/admin/search/">Search</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Borrowers<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/bodhi/admin/borrowers/search">Search/Update</a></li>
                <li><a href="/bodhi/admin/borrowers/add">Add</a></li>
              </ul>
            </li>
               <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Loans<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/bodhi/admin/loan/checkin">Check-in</a></li>
                <li><a href="/bodhi/admin/loan/checkout">Check-out</a></li>
              </ul>
            </li>
                <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fines<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/bodhi/admin/fines/compute">Compute Fines</a></li>
                <li><a href="/bodhi/admin/fines/collect">Collect Fines</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>