const AIAssistant = {
    init: function() {
        $(document).on('click', '.ai-toggle-btn', this.togglePanel);
        $(document).on('click', '#aiSubmit', this.submitQuery);
    },

    togglePanel: function() {
        $('.ai-content').toggleClass('show');
    },

    submitQuery: function() {
        const input = $('#aiInput').val();
        $.ajax({
            url: '/ai/assistant/chat',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({content: input}),
            success: function(res) {
                if (res.code === 200) {
                    AIAssistant.showResponse(res.data);
                } else {
                    AIAssistant.showError(res.msg);
                }
            },
            error: function(xhr) {
                AIAssistant.showError(xhr.responseJSON?.msg || '服务不可用');
            }
        });
    }
};
$(document).ready(() => AIAssistant.init());
