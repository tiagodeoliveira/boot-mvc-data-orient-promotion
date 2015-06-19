var config = {
    dataSource: "list.json",
    //theme: "white",
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
            "color"      : "#585858",
            "borderColor": "#585858",
            "radius": 16
        }
    }
}
alchemy = new Alchemy(config);