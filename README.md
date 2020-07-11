# ugiggle
Send trello cards, with attachments, via email. Used to send slush to my kindle.

## Required Environment Variables

* `TRELLO_KEY` - API key
* `TRELLO_SECRET` - API access token
* `AMAZON_SES_REGION` - AWS region, e.g. eu-west-1
* `UGIGGLE_RECIPIENT` - Email to send attachments to
* `UGIGGLE_SENDER` - Email to send from

## Requirements

* JDK 11+

## Structure/Dependencies

![modules](doc/images/reactor-graph.png)
