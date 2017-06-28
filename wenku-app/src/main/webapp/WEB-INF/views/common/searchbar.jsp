<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="searchbar container">
    <div class="row">
        <div class="col-md-3">
            <div class="logo">
                <img src="${ctx}/resources/img/logo.png" alt="" >
            </div>
        </div>
        <div class="col-md-9">
            <form action="${ctx}/search" class="search-form">
                <input type="text" id="query" name="query" class="query" placeholder="">
                <button type="submit" class="btn-search"> 搜一下 </button>
            </form>

        </div>
    </div>
</nav>
