<#import "parts/commonMacro.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Add your message" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

    <head>
        <!-- OpenLayers CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.2.1/css/ol.css" type="text/css">
        <style>
            .map {
                height: 400px;
                width: 100%;
            }
        </style>
        <!-- OpenLayers library -->
        <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.2.1/build/ol.js"></script>
    </head>
    <body>
    <h4>See your location on the map</h4>
    <div id="map" class="map"></div>
    <div id="info" style="display: none;"></div>
    <label for="track">track position
        <input id="track" type="checkbox"/>
    </label>
    <p>
        position accuracy : <code id="accuracy"></code>&nbsp;&nbsp;
        altitude : <code id="altitude"></code>&nbsp;&nbsp;
        altitude accuracy : <code id="altitudeAccuracy"></code>&nbsp;&nbsp;
        heading : <code id="heading"></code>&nbsp;&nbsp;
        speed : <code id="speed"></code>
    </p>
    <script type="text/javascript">
        var view = new ol.View({
            center: [0, 0],
            zoom: 2
        });

        var map = new ol.Map({
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                })
            ],
            target: 'map',
            view: view
        });

        var geolocation = new ol.Geolocation({
            // enableHighAccuracy must be set to true to have the heading value.
            trackingOptions: {
                enableHighAccuracy: true
            },
            projection: view.getProjection()
        });

        function el(id) {
            return document.getElementById(id);
        }

        el('track').addEventListener('change', function() {
            geolocation.setTracking(this.checked);
        });

        // update the HTML page when the position changes.
        geolocation.on('change', function() {
            el('accuracy').innerText = geolocation.getAccuracy() + ' [m]';
            el('altitude').innerText = geolocation.getAltitude() + ' [m]';
            el('altitudeAccuracy').innerText = geolocation.getAltitudeAccuracy() + ' [m]';
            el('heading').innerText = geolocation.getHeading() + ' [rad]';
            el('speed').innerText = geolocation.getSpeed() + ' [m/s]';
        });

        // handle geolocation error.
        geolocation.on('error', function(error) {
            var info = document.getElementById('info');
            info.innerHTML = error.message;
            info.style.display = '';
        });

        var accuracyFeature = new ol.Feature();
        geolocation.on('change:accuracyGeometry', function() {
            accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
        });

        var positionFeature = new ol.Feature();
        positionFeature.setStyle(new ol.style.Style({
            image: new ol.style.Circle({
                radius: 6,
                fill: new ol.style.Fill({
                    color: '#3399CC'
                }),
                stroke: new ol.style.Stroke({
                    color: '#fff',
                    width: 2
                })
            })
        }));

        geolocation.on('change:position', function() {
            var coordinates = geolocation.getPosition();
            positionFeature.setGeometry(coordinates ?
                new ol.geom.Point(coordinates) : null);
        });

        new ol.layer.Vector({
            map: map,
            source: new ol.source.Vector({
                features: [accuracyFeature, positionFeature]
            })
        });
    </script>
    </body>

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>