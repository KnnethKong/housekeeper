package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2017/5/22.
 */

public class LatLngEntity {

    /**
     * type : FeatureCollection
     * query : ["36","stevenston","安","莫","尔","bc","canada"]
     * features : [{"id":"place.16025353450317150","type":"Feature","place_type":["place"],"relevance":0.698,"properties":{"wikidata":"Q557967"},"text":"Anmore","place_name":"Anmore, British Columbia, Canada","matching_text":"安莫尔","matching_place_name":"安莫尔, British Columbia, Canada","bbox":[-122.894404389345,49.2993242358609,-122.821695670907,49.3845974994842],"center":[-122.8566,49.3183],"geometry":{"type":"Point","coordinates":[-122.8566,49.3183]},"context":[{"id":"postcode.17116463746353780","text":"V3H 4Y7"},{"id":"region.3747","short_code":"CA-BC","wikidata":"Q1974","text":"British Columbia"},{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]},{"id":"region.3747","type":"Feature","place_type":["region"],"relevance":0.5,"properties":{"wikidata":"Q1974","short_code":"CA-BC"},"text":"British Columbia","place_name":"British Columbia, Canada","matching_text":"BC","matching_place_name":"BC, Canada","bbox":[-139.06111,48.224554,-114.053857,60.002048],"center":[-125.460156,54.11661],"geometry":{"type":"Point","coordinates":[-125.460156,54.11661]},"context":[{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]},{"id":"poi.4622385771634720","type":"Feature","place_type":["poi"],"relevance":0.485,"properties":{"address":"999 Canada Pl","category":"building, structure","tel":"(604) 665-9000","wikidata":"Q1032014","landmark":true,"maki":"building"},"text":"Canada Place","place_name":"Canada Place, 999 Canada Pl, Vancouver, British Columbia V6C 3R9, Canada","matching_place_name":"Canada Place, 999 Canada Pl, Vancouver, BC V6C 3R9, Canada","center":[-123.111,49.2886],"geometry":{"coordinates":[-123.111,49.2886],"type":"Point"},"context":[{"id":"neighborhood.17765864241500530","text":"Gastown"},{"id":"place.5944060364130490","wikidata":"Q24639","text":"Vancouver"},{"id":"postcode.2248257411488880","text":"V6C 3R9"},{"id":"region.3747","short_code":"CA-BC","wikidata":"Q1974","text":"British Columbia"},{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]},{"id":"poi.5575056625163650","type":"Feature","place_type":["poi"],"relevance":0.485,"properties":{"address":"1501 Swift Creek Rd","category":"hotel, motel","tel":"(250) 566-8222","landmark":true,"maki":"lodging"},"text":"Canadas Best Value Inn","place_name":"Canadas Best Value Inn, 1501 Swift Creek Rd, Valemount, British Columbia, Canada","matching_place_name":"Canadas Best Value Inn, 1501 Swift Creek Rd, Valemount, BC, Canada","center":[-119.27875,52.8313],"geometry":{"coordinates":[-119.27875,52.8313],"type":"Point"},"context":[{"id":"place.10382255541162890","wikidata":"Q2508161","text":"Valemount"},{"id":"region.3747","short_code":"CA-BC","wikidata":"Q1974","text":"British Columbia"},{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]},{"id":"poi.7221550511525840","type":"Feature","place_type":["poi"],"relevance":0.485,"properties":{"address":"377 Old Hope Princeton Way","category":"hotel, motel","tel":"(604) 869-7107","landmark":true,"maki":"lodging"},"text":"Canadas Best Value Inn Hope","place_name":"Canadas Best Value Inn Hope, 377 Old Hope Princeton Way, Hope, British Columbia V0X 1L4, Canada","matching_place_name":"Canadas Best Value Inn Hope, 377 Old Hope Princeton Way, Hope, BC V0X 1L4, Canada","center":[-121.436107,49.375256],"geometry":{"coordinates":[-121.436107,49.375256],"type":"Point"},"context":[{"id":"place.1608022987274950","wikidata":"Q1610654","text":"Hope"},{"id":"postcode.6590752275449950","text":"V0X 1L4"},{"id":"region.3747","short_code":"CA-BC","wikidata":"Q1974","text":"British Columbia"},{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]}]
     * attribution : NOTICE: © 2017 Mapbox and its suppliers. All rights reserved. Use of this data is subject to the Mapbox Terms of Service (https://www.mapbox.com/about/maps/). This response and the information it contains may not be retained.
     */

    private String type;
    private String attribution;
    private List<String> query;
    private List<FeaturesBean> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public List<String> getQuery() {
        return query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }

    public List<FeaturesBean> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeaturesBean> features) {
        this.features = features;
    }

    public static class FeaturesBean {
        /**
         * id : place.16025353450317150
         * type : Feature
         * place_type : ["place"]
         * relevance : 0.698
         * properties : {"wikidata":"Q557967"}
         * text : Anmore
         * place_name : Anmore, British Columbia, Canada
         * matching_text : 安莫尔
         * matching_place_name : 安莫尔, British Columbia, Canada
         * bbox : [-122.894404389345,49.2993242358609,-122.821695670907,49.3845974994842]
         * center : [-122.8566,49.3183]
         * geometry : {"type":"Point","coordinates":[-122.8566,49.3183]}
         * context : [{"id":"postcode.17116463746353780","text":"V3H 4Y7"},{"id":"region.3747","short_code":"CA-BC","wikidata":"Q1974","text":"British Columbia"},{"id":"country.3179","short_code":"ca","wikidata":"Q16","text":"Canada"}]
         */

        private String id;
        private String type;
        private double relevance;
        private PropertiesBean properties;
        private String text;
        private String place_name;
        private String matching_text;
        private String matching_place_name;
        private GeometryBean geometry;
        private List<String> place_type;
        private List<Double> bbox;
        private List<Double> center;
        private List<ContextBean> context;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getRelevance() {
            return relevance;
        }

        public void setRelevance(double relevance) {
            this.relevance = relevance;
        }

        public PropertiesBean getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBean properties) {
            this.properties = properties;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getMatching_text() {
            return matching_text;
        }

        public void setMatching_text(String matching_text) {
            this.matching_text = matching_text;
        }

        public String getMatching_place_name() {
            return matching_place_name;
        }

        public void setMatching_place_name(String matching_place_name) {
            this.matching_place_name = matching_place_name;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public List<String> getPlace_type() {
            return place_type;
        }

        public void setPlace_type(List<String> place_type) {
            this.place_type = place_type;
        }

        public List<Double> getBbox() {
            return bbox;
        }

        public void setBbox(List<Double> bbox) {
            this.bbox = bbox;
        }

        public List<Double> getCenter() {
            return center;
        }

        public void setCenter(List<Double> center) {
            this.center = center;
        }

        public List<ContextBean> getContext() {
            return context;
        }

        public void setContext(List<ContextBean> context) {
            this.context = context;
        }

        public static class PropertiesBean {
            /**
             * wikidata : Q557967
             */

            private String wikidata;

            public String getWikidata() {
                return wikidata;
            }

            public void setWikidata(String wikidata) {
                this.wikidata = wikidata;
            }
        }

        public static class GeometryBean {
            /**
             * type : Point
             * coordinates : [-122.8566,49.3183]
             */

            private String type;
            private List<Double> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Double> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<Double> coordinates) {
                this.coordinates = coordinates;
            }
        }

        public static class ContextBean {
            /**
             * id : postcode.17116463746353780
             * text : V3H 4Y7
             * short_code : CA-BC
             * wikidata : Q1974
             */

            private String id;
            private String text;
            private String short_code;
            private String wikidata;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getShort_code() {
                return short_code;
            }

            public void setShort_code(String short_code) {
                this.short_code = short_code;
            }

            public String getWikidata() {
                return wikidata;
            }

            public void setWikidata(String wikidata) {
                this.wikidata = wikidata;
            }
        }
    }
}
