<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Blog</title>
</head>
<body>
    
    <input type="hidden" id="hidden-user" value="${user}">
    <input type="hidden" id="hidden-receiver" value="${receiver}">
    
    <div class="header">
        <div class="chat-container">
            <div class="header-links">
                <a id="to-home" href="home">
                    <i class="bi bi-house-door"></i>
                </a>
            </div>
        </div>
    </div>

    <main class="content">
        <div class="chat-container p-0">
            <div class="card">
                <div class="row g-0">
                    <div class="col-12 col-lg-5 col-xl-3 border-right">
                        <div class="input-group pple">
                            <h2>Online Users</h2>
                        </div>
                        <div id="people-list">

                        </div>
                        <hr class="d-block d-lg-none mt-1 mb-0">
                    </div>

                    <div class="col-12 col-lg-7 col-xl-9">
                        <div class="py-2 px-4 border-bottom d-none d-lg-block">
                            <div id="d-flex" class="d-flex align-items-center py-1">
                                
                            </div>
                        </div>
    
                        <div class="position-relative">
                            <div id="p-4" class="chat-messages p-4">
    
                                
                            </div>
    
                            <div class="flex-grow-0 py-3 px-4 border-top">
                                <div class="input-group">
                                    <input type="text" id="form-control" placeholder="Type your message">
                                    <button onclick="send()" id="btn-send" class="btn btn-primary">Send</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <script src="javascript/privatechat.js"></script>
    
</body>
</html>

<!--    <main class="content">
        <div class="chat-container p-0">
            <div class="row g-0 full">
                <div id="bo" class="col-12 col-lg-7 col-xl-9">
                    <div class="py-2 px-4 border-bottom d-none d-lg-block">
                        <div class="d-flex align-items-center py-1">
                            <div id="col-lg-6">
                                
                            </div>
                        </div>
                    </div>

                    <div class="position-relative">
                        <div id="p-4" class="chat-messages p-4">

                        </div>

                        <div class="flex-grow-0 py-3 px-4 border-top">
                            <div class="input-group">
                                <input type="text" id="form-control" placeholder="Type your message">
                                <button id="btn-send" onclick="send()" class="btn btn-primary">Send</button>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </main>-->