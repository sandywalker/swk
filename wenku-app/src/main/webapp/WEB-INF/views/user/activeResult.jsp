<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>

<layout:default title="搜文酷 邮件激活">
    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                   <c:choose>
                       <c:when test="${result.success}">
                           <div class="alert alert-success">
                               <p>${result.message} <a href="${ctx}/login?username=${mail}" class="btn btn-sm btn-default">点击登录</a>
                               </p>
                           </div>
                        </c:when>
                       <c:otherwise>
                           <div class="alert alert-warning">
                               <p>
                                       ${result.message}
                                   <c:if test="${result.resendMail}" >
                                       <a href="${ctx}/register/sendMail?mail=${mail}" class="btn btn-sm btn-default"
                                          id="resendMail">重新发送激活邮件</a>
                                   </c:if>
                               </p>
                           </div>
                       </c:otherwise>
                   </c:choose>
                </div>
            </div>
        </div>

    </jsp:attribute>
    <jsp:attribute name="js">
        <script type="text/javascript">
            (function () {
                $('#resendMail').on('click', function (e) {
                    e.preventDefault();
                    var url = $(this).attr('href');
                    $.get(url, function (result) {
                        if (result === 'success') {
                            alert('邮件发送成功!');
                            location.href = ctx;
                        } else {
                            alert('邮件发送失败！');
                        }
                    })
                });
            })();
        </script>
    </jsp:attribute>
</layout:default>