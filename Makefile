# -----
#  Makefile for kosmos-web
#

#
# adjust if these are not on your path
#
CLJ := clj
CLOJURE := clojure

#
# Targets
#
.PHONY: clean dev attach test tag

#
# run lein for dev repl
#
nrepl:
	@${CLOJURE} -Mnrepl

test:
	@${CLOJURE} -Mdev:test

attach: PORT=$(shell cat .nrepl-port)
attach:
	@${CLOJURE} -Mattach --attach localhost:${PORT}

clean:
	@rm -rf .cpcache/ target/

tag:	VERSION := $(shell next-version .version)
tag:
	git tag -a ${VERSION} -m "release/tag version v${VERSION}"
