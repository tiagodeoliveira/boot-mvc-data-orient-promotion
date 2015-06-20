function graphBuilder() {
    console.log("Building the graph");
    var promo = $('#promotions').val();
    promo = promo.replace('#', '');
    d3.select("svg").remove();
    var config = {
        dataSource: "list.json?promotion=" + promo,
        edgeTypes: {"edgeType":["BOUGHT", "BELONGS_TO", "DONE_BY", "HAS"]},
        nodeTypes: {"nodeType":["client", "store", "promotion", "sale"]},
        forceLocked: false,
        directedEdges: true,
        nodeCaption: 'caption',
        edgeCaption: 'edgeType',
        nodeCaptionsOnByDefault: false,
        edgeCaptionsOnByDefault: true,
        curvedEdges: true,
        nodeStyle: {
            "client": {
                "color"      : "#0431B4",
                "borderColor": "#0431B4",
                "radius": 16
            },
            "store": {
                "color"      : "#C8C8C8",
                "borderColor": "#C8C8C8",
                "radius": 16
            },
            "promotion": {
                "color"      : "#8A0808",
                "borderColor": "#8A0808",
                "radius": 16
            },
            "sale": {
                "color"      : "#F7FE2E",
                "borderColor": "#F7FE2E",
                "radius": 16
            }
        }
    }
    return new Alchemy(config);
}

var alchemy;
$(function() {
    alchemy = graphBuilder();
});