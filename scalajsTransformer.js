'use strict';
// https://github.com/ds300/react-native-typescript-transformer/blob/master/index.js
let upstreamTransformer = null;

try {
    // handle RN >= 0.47
    upstreamTransformer = require('metro-bundler/src/transformer')
} catch (e) {
    try {
        // handle RN 0.46
        upstreamTransformer = require('metro-bundler/build/transformer')
    } catch (e) {
        // handle RN <= 0.45
        const oldUpstreamTransformer = require('react-native/packager/transformer');
        upstreamTransformer = {
            transform({src, filename, options}) {
                return oldUpstreamTransformer.transform(src, filename, options)
            },
        }
    }
}

module.exports.transform = (src, filename, options) => {
    if (typeof src === 'object') {
        // handle RN >= 0.46
        ({src, filename, options} = src)
    }
    if (filename.indexOf('scalajs-output-') > -1) {
        return {
            ast: null,
            code: src,
            filename,
            // TODO: generate source map
            // map: filename + '.map'
        };
    } else {
        return upstreamTransformer.transform({src, filename, options});
    }
};
